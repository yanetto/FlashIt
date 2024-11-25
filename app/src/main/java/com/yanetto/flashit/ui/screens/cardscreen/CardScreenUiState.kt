package com.yanetto.flashit.ui.screens.cardscreen

import com.yanetto.flashit.core.domain.model.Card

data class CardScreenUiState (
    val cards: List<Card> = listOf(),
    val currentCard: Card = Card(),
    val currentCardIndex: Int = 0,
    val setName: String = "",
    val showFinishScreen: Boolean = false
)