package com.yanetto.card_set_managment.domain

import com.yanetto.core.domain.model.CardSet

interface UpdateSetUseCase {
    suspend operator fun invoke(cardSet: CardSet)
}