package com.yanetto.card_learn.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.card_learn.R
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun CardScreen(
    setId: Int? = null,
    modifier: Modifier = Modifier,
    viewModel: CardScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    var offsetX by remember { mutableFloatStateOf(0f) }

    BackHandler {
        onBackClick()
    }

    LaunchedEffect(setId) {
        if (setId != null) {
            Log.d("SET_ID", setId.toString())
            viewModel.updateSetId(setId)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = uiState.value.setName,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(16.dp))

        val counter = "${uiState.value.currentCardIndex + 1}/${uiState.value.cards.size}"
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = counter,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        if (!uiState.value.showFinishScreen) {
            RotatingCard(
                viewModel = viewModel,
                offsetX = offsetX,
                onResetOffset = { offsetX = 0f }
            )

            Spacer(modifier = Modifier.size(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Column (
                    modifier = Modifier.weight(1.5f)
                ) {
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFf4b1ab), CircleShape)
                            .clickable {
                                offsetX = -1000f
                                viewModel.addCard(uiState.value.currentCard)
                            }
                            .align(Alignment.CenterHorizontally),
                        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.background)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = null,
                            tint = Color(0xFFf4b1ab),
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Не знаю",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFf4b1ab),
                        modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.weight(2.5f))

                Column (
                    modifier = Modifier.weight(1.5f)
                ) {
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFa8d5ba), CircleShape)
                            .clickable {
                                offsetX = 1000f
                            }
                            .align(Alignment.CenterHorizontally),
                        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.background)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check),
                            tint = Color(0xFFa8d5ba),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }
                    Text(
                        text = "Знаю",
                        color = Color(0xFFa8d5ba),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
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
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(8f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Все карточки изучены!",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.size(32.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                        .clickable {
                            onBackClick()
                        },
                    colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = "Продолжить",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}


const val ANIMATION_DURATION = 600
const val FULL_TURN = 360
const val HALF_TURN = 180
const val DENSITY_MULTIPLIER = 12

@Composable
fun ColumnScope.RotatingCard(
    viewModel: CardScreenViewModel,
    offsetX: Float,
    onResetOffset: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    var rotationAngle by remember { mutableFloatStateOf(0f) }

    val rotation by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        label = "RotateAnimation"
    )

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        finishedListener = {
            if (offsetX != 0f) {
                if (rotationAngle % FULL_TURN != 0f) rotationAngle += HALF_TURN
                viewModel.nextCard()
                onResetOffset()
            }
        },
        label = "OffsetAnimation"
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
                translationX = animatedOffsetX
                rotationY = rotation % FULL_TURN
                cameraDistance = DENSITY_MULTIPLIER * density
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
                    text = uiState.value.currentCard.question,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            } else {
                MarkdownText(
                    markdown = uiState.value.currentCard.answer,
                    //color = Color.Black,
                    style = MaterialTheme.typography.bodySmall,
                    //textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}