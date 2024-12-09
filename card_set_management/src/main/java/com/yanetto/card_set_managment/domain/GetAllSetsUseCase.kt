package com.yanetto.card_set_managment.domain

import com.yanetto.core.domain.model.CardSet
import kotlinx.coroutines.flow.Flow

interface GetAllSetsUseCase {
    operator fun invoke(): Flow<List<CardSet>>
}