package com.yanetto.card_learn.domain.impl

import com.yanetto.card_learn.domain.GetCardSetWithCardsUseCase
import com.yanetto.core.domain.model.CardSetWithCards
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardSetWithCardsUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetCardSetWithCardsUseCase {
    override operator fun invoke(setId: Int): Flow<CardSetWithCards?> = localDataSourceRepository.getCardSetWithCards(setId)
}