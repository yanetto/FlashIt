package com.yanetto.card_grid.domain.impl

import com.yanetto.card_grid.domain.GetCardSetByIdUseCase
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardSetByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetCardSetByIdUseCase {
    override operator fun invoke(setId: Int): Flow<CardSet?> = localDataSourceRepository.getCardSetByIdFlow(setId)
}