package com.yanetto.card_set_managment.domain.impl

import com.yanetto.card_set_managment.domain.InsertSetUseCase
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class InsertSetUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : InsertSetUseCase {
    override suspend fun invoke(cardSet: CardSet) {
        localDataSourceRepository.insertCardSet(cardSet)
    }
}