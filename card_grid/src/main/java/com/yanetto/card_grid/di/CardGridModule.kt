package com.yanetto.card_grid.di

import com.yanetto.card_grid.domain.GetCardSetByIdUseCase
import com.yanetto.card_grid.domain.GetCardSetWithCardsUseCase
import com.yanetto.card_grid.domain.impl.GetCardSetByIdUseCaseImpl
import com.yanetto.card_grid.domain.impl.GetCardSetWithCardsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CardGridModule {
    @Binds
    abstract fun bindGetCardSetByIdUseCase(
        getCardSetByIdUseCaseImpl: GetCardSetByIdUseCaseImpl
    ) : GetCardSetByIdUseCase

    @Binds
    abstract fun bindGetCardSetWithCardsUseCase(
        getCardSetWithCardsUseCaseImpl: GetCardSetWithCardsUseCaseImpl
    ) : GetCardSetWithCardsUseCase
}