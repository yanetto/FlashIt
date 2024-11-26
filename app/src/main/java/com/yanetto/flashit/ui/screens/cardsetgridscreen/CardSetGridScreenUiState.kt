package com.yanetto.flashit.ui.screens.cardsetgridscreen

import com.yanetto.core.domain.model.Card

data class CardSetGridScreenUiState (
    val setId: Int = 0,
    val setName: String = "",
    val cards: List<Card> = listOf()
)