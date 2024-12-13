package com.yanetto.card_editor.di

import com.yanetto.card_editor.domain.DeleteCardUseCase
import com.yanetto.card_editor.domain.GetCardByIdUseCase
import com.yanetto.card_editor.domain.InsertCardUseCase
import com.yanetto.card_editor.domain.UpdateCardUseCase
import com.yanetto.card_editor.domain.impl.DeleteCardUseCaseImpl
import com.yanetto.card_editor.domain.impl.GetCardByIdUseCaseImpl
import com.yanetto.card_editor.domain.impl.InsertCardUseCaseImpl
import com.yanetto.card_editor.domain.impl.UpdateCardUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CardEditorModule {
    @Binds
    abstract fun bindDeleteCardUseCase(
        deleteCardUseCaseImpl: DeleteCardUseCaseImpl
    ) : DeleteCardUseCase

    @Binds
    abstract fun bindInsertCardUseCase(
        insertCardUseCaseImpl: InsertCardUseCaseImpl
    ) : InsertCardUseCase

    @Binds
    abstract fun bindUpdateCardUseCase(
        updateCardUseCaseImpl: UpdateCardUseCaseImpl
    ) : UpdateCardUseCase

    @Binds
    abstract fun bindGetCardByIdUseCase(
        getCardByIdUseCaseImpl: GetCardByIdUseCaseImpl
    ) : GetCardByIdUseCase
}