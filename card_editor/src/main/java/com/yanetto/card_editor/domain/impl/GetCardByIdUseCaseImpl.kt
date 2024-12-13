package com.yanetto.card_editor.domain.impl

import com.yanetto.card_editor.domain.GetCardByIdUseCase
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetCardByIdUseCase {
    override fun invoke(cardId: Int): Flow<Card> = localDataSourceRepository.getCardByIdFlow(cardId)
}