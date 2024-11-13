package com.yanetto.flashit.ui.screens.cardscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.flashit.R

@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    viewModel: CardScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = "Подготовка к себесу",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = "1/10",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        RotatingCard(viewModel = viewModel)

        Spacer(modifier = Modifier.size(32.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Card (
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dont_know),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.weight(2.5f))

            Card (
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.know),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = stringResource(R.string.tap_on_card_to_flip_it),
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            textAlign = TextAlign.Center
        )
    }
}

const val ANIMATION_DURATION = 600
const val FULL_TURN = 360
const val HALF_TURN = 180
const val DENSITY_MULTIPLIER = 12

@Composable
fun ColumnScope.RotatingCard (
    viewModel: CardScreenViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    var rotationAngle by remember { mutableStateOf(0f) }

    val rotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        label = "RotateAnimation"
    )

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(8f)
            .graphicsLayer {
                rotationY = rotation % FULL_TURN
                cameraDistance =
                    DENSITY_MULTIPLIER * density
            }
            .clickable { rotationAngle += HALF_TURN }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX =
                        if ((rotation % FULL_TURN) > HALF_TURN * 0.5f && (rotation % FULL_TURN) < FULL_TURN * 0.75f) -1f
                        else 1f
                },
            contentAlignment = Alignment.Center
        ) {
            if ((rotation % FULL_TURN) < HALF_TURN * 0.5f || (rotation % FULL_TURN) >= FULL_TURN * 0.75f) {
                Text(
                    text = uiState.value.question,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = uiState.value.answer,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen()
}