package com.yanetto.flashit.ui.screens.cardsetscreen

import com.yanetto.flashit.domain.model.CardSet

data class CardSetScreenUiState (
    val cardSets: List<CardSet> = listOf(),
    val currentSet: CardSet = CardSet(),
    val newName: String = ""
)