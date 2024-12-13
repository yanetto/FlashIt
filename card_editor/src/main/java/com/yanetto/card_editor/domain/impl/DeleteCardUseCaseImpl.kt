package com.yanetto.card_editor.domain.impl

import com.yanetto.card_editor.domain.DeleteCardUseCase
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class DeleteCardUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteCardUseCase{
    override suspend fun invoke(card: Card) {
        localDataSourceRepository.deleteCard(card)
    }
}