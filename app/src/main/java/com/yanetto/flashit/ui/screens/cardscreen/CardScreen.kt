package com.yanetto.flashit.ui.screens.cardscreen

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yanetto.flashit.R

@Composable
fun CardScreen(
    modifier: Modifier = Modifier
) {
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
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = "1/10",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(16.dp))

        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardColors(
                containerColor = Color(168, 153, 255),
                contentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(0.5f),
                disabledContentColor = Color.White.copy(0.5f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Extension-функции в Kotlin",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

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
                    .border(1.dp, Color.Black, CircleShape)
                    .clickable {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dont_know),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp).padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.weight(2.5f))

            Card (
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
                    .clickable {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.know),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp).padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = "Нажмите на карточку, чтобы перевернуть ее",
            color = Color.Black.copy(0.4f),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen()
}