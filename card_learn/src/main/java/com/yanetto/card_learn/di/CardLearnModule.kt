package com.yanetto.card_learn.di

import com.yanetto.card_learn.domain.GetCardSetByIdUseCase
import com.yanetto.card_learn.domain.GetCardSetWithCardsUseCase
import com.yanetto.card_learn.domain.impl.GetCardSetByIdUseCaseImpl
import com.yanetto.card_learn.domain.impl.GetCardSetWithCardsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CardLearnModule {
    @Binds
    abstract fun bindGetCardSetByIdUseCase(
        getCardSetByIdUseCaseImpl: GetCardSetByIdUseCaseImpl
    ) : GetCardSetByIdUseCase

    @Binds
    abstract fun bindGetCardSetWithCardsUseCase(
        getCardSetWithCardsUseCaseImpl: GetCardSetWithCardsUseCaseImpl
    ) : GetCardSetWithCardsUseCase
}