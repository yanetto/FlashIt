package com.yanetto.flashit.ui.screens.editscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.flashit.R

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    cardId: Int = 0,
    setId: Int = 0,
    onDoneClick: (Int) -> Unit,
    viewModel: EditScreenViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(cardId) {
        if (cardId != 0) {
            Log.d("CARD_ID", cardId.toString())
            viewModel.updateCardId(cardId)
        }
    }

    LaunchedEffect(setId) {
        if (setId != 0) {
            Log.d("SET_ID", setId.toString())
            viewModel.updateSetId(setId)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .imePadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .clip(MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = if (uiState.value.currentCard.id == 0) "Новая карточка" else "Редактирование карточки",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {
            EditTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.value.currentCard.question,
                onValueChange = { viewModel.changeQuestion(it) },
                textStyle = MaterialTheme.typography.titleSmall,
                hint = "Введите вопрос"
            )

            EditTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = uiState.value.currentCard.answer,
                onValueChange = { viewModel.changeAnswer(it) },
                textStyle = MaterialTheme.typography.bodyMedium,
                hint = "Введите ответ"
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = "Редактировать",
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
                Spacer(modifier = Modifier.size(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Готово",
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable {
                            if (uiState.value.currentCard.id == 0) viewModel.addCard(uiState.value.currentCard)
                            else viewModel.updateCard(uiState.value.currentCard)
                            onDoneClick(uiState.value.currentCard.setId)
                        }
                )
            }
        }
    }
}

@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    // Синхронизируем textValue с value
    var textValue by remember { mutableStateOf(TextFieldValue(value)) }
    LaunchedEffect(value) {
        if (textValue.text != value) {
            textValue = TextFieldValue(value)
        }
    }

    val color = MaterialTheme.colorScheme.onBackground
    val hintColor =
        if (textValue.text.isEmpty()) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else MaterialTheme.colorScheme.onSurface

    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onValueChange(it.text)
        },
        singleLine = false,
        modifier = modifier.padding(8.dp),
        textStyle = textStyle.copy(textAlign = TextAlign.Start, color = color),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (textValue.text.isEmpty()) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = hintColor,
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }
            it()
        }
    }
}
