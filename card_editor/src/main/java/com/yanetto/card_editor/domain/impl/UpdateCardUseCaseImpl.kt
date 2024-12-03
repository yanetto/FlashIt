package com.yanetto.card_editor.domain.impl

import com.yanetto.card_editor.domain.UpdateCardUseCase
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class UpdateCardUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateCardUseCase {
    override suspend fun invoke(card: Card) {
        localDataSourceRepository.updateCard(card)
    }
}