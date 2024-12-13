package com.yanetto.card_learn.presentation

import com.yanetto.core.domain.model.Card

data class CardScreenUiState (
    val cards: List<Card> = listOf(),
    val currentCard: Card = Card(),
    val currentCardIndex: Int = 0,
    val setName: String = "",
    val showFinishScreen: Boolean = false
)