package com.yanetto.card_editor.domain

import com.yanetto.core.domain.model.Card

interface InsertCardUseCase {
    suspend operator fun invoke(card: Card)
}