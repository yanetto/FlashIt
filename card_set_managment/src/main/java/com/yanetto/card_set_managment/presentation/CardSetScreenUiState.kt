package com.yanetto.card_set_managment.presentation

import com.yanetto.core.domain.model.CardSet

data class CardSetScreenUiState (
    val cardSets: List<CardSet> = listOf(),
    val currentSet: CardSet = CardSet(),
    val newName: String = ""
)