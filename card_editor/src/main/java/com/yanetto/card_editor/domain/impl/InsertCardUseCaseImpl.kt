package com.yanetto.card_editor.domain.impl

import com.yanetto.card_editor.domain.InsertCardUseCase
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class InsertCardUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : InsertCardUseCase {
    override suspend fun invoke(card: Card) {
        localDataSourceRepository.insertCard(card)
    }
}