package com.yanetto.card_editor.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.card_editor.R

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    cardId: Int = 0,
    setId: Int = 0,
    onDoneClick: (Int) -> Unit,
    viewModel: EditScreenViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    var isPopupVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

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

    EditPopup(
        isVisible = isPopupVisible,
        onDeleteButtonClick = {
            viewModel.deleteCard(uiState.value.currentCard)
            onDoneClick(uiState.value.currentCard.setId)
        },
        onCopyButtonClick = {
            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = ClipData.newPlainText(
                "CARD_TEXT",
                "${uiState.value.currentCard.question}\n${uiState.value.currentCard.answer}"
            )
            clipboardManager.setPrimaryClip(text)
        },

        onGenerateButtonClick = { viewModel.changeIsAnswerLoading(true); viewModel.getGptResponse(context); },
        onDismiss = { isPopupVisible = false }
    )

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
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
                    .clickable { onDoneClick(uiState.value.currentCard.setId) },
                painter = painterResource(R.drawable.back),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = if (uiState.value.currentCard.id == 0) "Новая карточка" else "Редактирование",
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
                maxLines = 3,
                value = uiState.value.currentCard.question,
                onValueChange = { viewModel.changeQuestion(it) },
                textStyle = MaterialTheme.typography.bodyLarge,
                hint = "Введите вопрос"
            )

            if (!uiState.value.isAnswerLoading) {
                EditTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    value = uiState.value.currentCard.answer,
                    onValueChange = { viewModel.changeAnswer(it) },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    hint = "Введите ответ"
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    SynchronizedShimmeringTexts()
                }
            }


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
                        .clickable { isPopupVisible = true }
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
fun SynchronizedShimmeringTexts() {
    val transition = rememberInfiniteTransition(label = "skeletonShimmer")

    val translateAnimation by transition.animateFloat(
        label = "skeletonAnimation",
        initialValue = -400f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Restart
        )
    )

    val shimmerColors = listOf(
        Color(0xFF98ADFE),
        Color(0xFFD7AEEB),
        Color(0xFFF7E1EE)
    )

    val shimmerBrush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, 0f),
        end = Offset(translateAnimation + 200f, 200f),
        tileMode = TileMode.Mirror
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(4) {
            Text(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .background(shimmerBrush, RoundedCornerShape(12.dp)),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = "                     ",
            modifier = Modifier
                .padding(vertical = 3.dp)
                .background(shimmerBrush, RoundedCornerShape(12.dp)),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    hint: String,
    value: String,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

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
        maxLines = maxLines,
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

@Composable
fun EditPopup(
    isVisible: Boolean,
    onDeleteButtonClick: () -> Unit,
    onCopyButtonClick: () -> Unit,
    onGenerateButtonClick: () -> Unit,
    onDismiss: () -> Unit = {}
){
    val interactionSource = remember { MutableInteractionSource() }
    if (isVisible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onDismiss
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Card {
                        Column {
                            SimpleTextButton(
                                text = "Скопировать текст",
                                onClick = { onCopyButtonClick(); onDismiss() },
                                painter = painterResource(id = R.drawable.copy),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            SimpleTextButton(
                                text = "Сгенерировать ответ",
                                onClick = { onGenerateButtonClick(); onDismiss() },
                                painter = painterResource(id = R.drawable.generate),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            SimpleTextButton(
                                text = "Удалить",
                                onClick = { onDeleteButtonClick(); onDismiss() },
                                color = MaterialTheme.colorScheme.error,
                                painter = painterResource(id = R.drawable.delete),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Card {
                        SimpleTextButton(
                            text = "Закрыть",
                            onClick = onDismiss,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun SimpleTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    painter: Painter? = null,
){
    val iconColor = if (color != Color.Unspecified) color else LocalContentColor.current
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
    ){
        Box(
            modifier = modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        ){
            if (painter != null) {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Text(
                text = text,
                color = color,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

}
