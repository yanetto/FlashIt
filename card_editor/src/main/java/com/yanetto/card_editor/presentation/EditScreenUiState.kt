package com.yanetto.card_editor.presentation

import com.yanetto.core.domain.model.Card

data class EditScreenUiState (
    val currentCard: Card =
        Card(
            question = "",
            answer = ""
        ),
    val isAnswerLoading: Boolean = false
)