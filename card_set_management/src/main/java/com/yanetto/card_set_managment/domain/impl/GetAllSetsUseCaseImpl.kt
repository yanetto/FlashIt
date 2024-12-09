package com.yanetto.card_set_managment.domain.impl

import com.yanetto.card_set_managment.domain.GetAllSetsUseCase
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSetsUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllSetsUseCase {
    override fun invoke(): Flow<List<CardSet>> = localDataSourceRepository.getAllCardSetsFlow()
}