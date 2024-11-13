package com.yanetto.flashit.ui.screens.cardsetscreen

import com.yanetto.flashit.domain.model.CardSet

data class CardSetScreenUiState (
    val cardSet: List<CardSet> = listOf(),
    val newName: String = ""
)