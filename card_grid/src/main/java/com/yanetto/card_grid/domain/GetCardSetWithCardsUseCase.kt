package com.yanetto.card_grid.domain

import com.yanetto.core.domain.model.CardSetWithCards
import kotlinx.coroutines.flow.Flow

interface GetCardSetWithCardsUseCase {
    operator fun invoke(setId:Int): Flow<CardSetWithCards?>
}