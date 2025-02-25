package com.yanetto.card_grid.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.card_grid.R

@Composable
fun GridScreen(
    modifier: Modifier = Modifier,
    setId: Int? = null,
    onEditClick: (Int) -> Unit,
    onCreateClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: GridScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

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
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(horizontal = 8.dp)
                .clip(MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() },
                painter = painterResource(R.drawable.back),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = uiState.value.setName,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {
            items(uiState.value.cards.size) { i ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            onEditClick(uiState.value.cards[i].id)
                        },
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .height(175.dp)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = uiState.value.cards[i].question,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            onCreateClick(uiState.value.setId)
                        },
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .height(175.dp)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Добавить карточку",
                            modifier =Modifier.size(36.dp)
                        )
                    }
                }
            }
        }
    }
}
