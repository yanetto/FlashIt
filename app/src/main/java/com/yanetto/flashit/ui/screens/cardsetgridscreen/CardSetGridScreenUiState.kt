package com.yanetto.flashit.ui.screens.cardsetgridscreen

import com.yanetto.flashit.domain.model.Card

data class CardSetGridScreenUiState (
    val setId: Int = 1,
    val cards: List<Card> = listOf()
)