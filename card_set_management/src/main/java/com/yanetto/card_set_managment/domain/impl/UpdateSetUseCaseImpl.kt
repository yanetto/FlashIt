package com.yanetto.card_set_managment.domain.impl

import com.yanetto.card_set_managment.domain.UpdateSetUseCase
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class UpdateSetUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateSetUseCase {
    override suspend fun invoke(cardSet: CardSet) {
        localDataSourceRepository.updateCardSet(cardSet)
    }
}