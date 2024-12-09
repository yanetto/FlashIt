package com.yanetto.card_set_managment.domain.impl

import com.yanetto.card_set_managment.domain.DeleteSetUseCase
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class DeleteSetUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteSetUseCase {
    override suspend operator fun invoke(cardSet: CardSet) {
        localDataSourceRepository.deleteCardSetWithCards(cardSet)
    }
}