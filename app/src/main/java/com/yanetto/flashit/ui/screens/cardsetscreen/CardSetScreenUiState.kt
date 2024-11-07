package com.yanetto.flashit.ui.screens.cardsetscreen

import com.yanetto.flashit.domain.model.CardSet

data class CardSetScreenUiState (
    val cardSet: List<CardSet> = listOf(
        CardSet(0, "Подготовка к собесу"),
        CardSet(1, "Японский язык"),
        CardSet(2, "Подготовка к экзамену")
    )
)