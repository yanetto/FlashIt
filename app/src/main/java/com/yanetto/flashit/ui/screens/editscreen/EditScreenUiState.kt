package com.yanetto.flashit.ui.screens.editscreen

import com.yanetto.flashit.core.domain.model.Card

data class EditScreenUiState (
    val currentCard: Card =
        Card(
            question = "",
            answer = ""
        )
)