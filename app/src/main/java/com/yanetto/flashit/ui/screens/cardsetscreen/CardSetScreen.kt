package com.yanetto.flashit.ui.screens.cardsetscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yanetto.flashit.R

@Composable
fun CardSetScreen(
    modifier: Modifier = Modifier,
    viewModel: CardSetScreenViewModel = CardSetScreenViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().weight(0.5f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Наборы карточек",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Добавить набор",
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterEnd)
                    .clickable { }
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {
            items(uiState.value.cardSet) { cardSet ->
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(168, 153, 255),
                        contentColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = cardSet.name,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.learn),
                            contentDescription = "Учить",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .clickable { }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Редактировать",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .clickable { }
                        )
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}