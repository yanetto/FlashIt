package com.yanetto.card_grid.domain

import com.yanetto.core.domain.model.CardSet
import kotlinx.coroutines.flow.Flow

interface GetCardSetByIdUseCase {
    operator fun invoke(setId: Int): Flow<CardSet?>
}