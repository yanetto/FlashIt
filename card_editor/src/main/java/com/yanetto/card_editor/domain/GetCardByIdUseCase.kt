package com.yanetto.card_editor.domain


import com.yanetto.core.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface GetCardByIdUseCase {
    operator fun invoke(cardId: Int): Flow<Card>
}