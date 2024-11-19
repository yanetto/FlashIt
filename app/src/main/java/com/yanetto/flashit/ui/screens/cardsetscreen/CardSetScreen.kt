package com.yanetto.flashit.ui.screens.cardsetscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.yanetto.flashit.R
import com.yanetto.flashit.domain.model.CardSet

enum class DialogType {
    NONE,
    CREATE,
    EDIT
}

@Composable
fun CardSetScreen(
    modifier: Modifier = Modifier,
    onCardSetEditClick: (Int) -> Unit,
    onCardSetPlayClick: () -> Unit,
    viewModel: CardSetScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(DialogType.NONE) }
    var isPopupVisible by remember { mutableStateOf(false) }

    BackHandler { }

    EditPopup(
        isVisible = isPopupVisible,
        onDeleteButtonClick = { viewModel.deleteSet(uiState.value.currentSet) },
        onEditButtonClick = { showDialog = DialogType.EDIT },
        onChangeColorClick = {  },
        onDismiss = { isPopupVisible = false }
    )

    if (showDialog != DialogType.NONE) {
        EnterNameDialog(
            text = when (showDialog) {
                DialogType.EDIT -> "Введите новое название"
                DialogType.CREATE -> "Введите название набора"
                DialogType.NONE -> ""
            },
            viewModel = viewModel,
            onDismiss = { showDialog = DialogType.NONE },
            onConfirmClick = {
                if (showDialog == DialogType.CREATE) {
                    viewModel.addSet(it)
                } else {
                    viewModel.updateSet(it)
                }
                showDialog = DialogType.NONE
            },
            currentName =
            when (showDialog) {
                DialogType.EDIT -> uiState.value.currentSet.name
                DialogType.CREATE -> ""
                DialogType.NONE -> ""
            },
        )
    }
    val mod: Modifier = if (showDialog != DialogType.NONE) {
        modifier.blur(20.dp)
    } else modifier

    Column(
        modifier = mod
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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
                text = "Наборы карточек",
                style = MaterialTheme.typography.bodyLarge,
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
                    .clickable { showDialog = DialogType.CREATE }
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ) {
            items(uiState.value.cardSets) { cardSet ->
                Card(
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            onCardSetEditClick(cardSet.id)
                        }
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(6f),
                            text = cardSet.name,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.learn),
                            contentDescription = "Учить",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .clickable { onCardSetPlayClick() }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "Редактировать",
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.setCurrentCardSet(cardSet)
                                    isPopupVisible = true
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnterNameDialog(
    text: String,
    currentName: String,
    viewModel: CardSetScreenViewModel,
    onDismiss: () -> Unit = {},
    onConfirmClick: (CardSet) -> Unit = {}
) {
    var name by remember { mutableStateOf(currentName) }
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Card (
                modifier = Modifier.padding(32.dp)
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = text,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            viewModel.changeName(name)
                        },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Назад",
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { onDismiss() }
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "Далее",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { onConfirmClick(viewModel.uiState.value.currentSet.copy(name = name)) }
                        )
                    }
                }
            }
        }
    )
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

@Composable
fun EditPopup(
    isVisible: Boolean,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    onChangeColorClick: () -> Unit,
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
                                text = "Поменять цвет",
                                onClick = { onChangeColorClick(); onDismiss() },
                                painter = painterResource(id = R.drawable.choose_color),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            SimpleTextButton(
                                text = "Изменить название",
                                onClick = { onEditButtonClick(); onDismiss() },
                                painter = painterResource(id = R.drawable.edit),
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