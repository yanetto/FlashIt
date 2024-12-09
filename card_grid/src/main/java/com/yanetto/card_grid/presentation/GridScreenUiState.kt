package com.yanetto.card_grid.presentation

import com.yanetto.core.domain.model.Card

data class GridScreenUiState (
    val setId: Int = 0,
    val setName: String = "",
    val cards: List<Card> = listOf()
)