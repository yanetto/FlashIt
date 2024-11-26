package com.yanetto.card_set_managment.di

import com.yanetto.card_set_managment.domain.DeleteSetUseCase
import com.yanetto.card_set_managment.domain.GetAllSetsUseCase
import com.yanetto.card_set_managment.domain.InsertSetUseCase
import com.yanetto.card_set_managment.domain.UpdateSetUseCase
import com.yanetto.card_set_managment.domain.impl.DeleteSetUseCaseImpl
import com.yanetto.card_set_managment.domain.impl.GetAllSetsUseCaseImpl
import com.yanetto.card_set_managment.domain.impl.InsertSetUseCaseImpl
import com.yanetto.card_set_managment.domain.impl.UpdateSetUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CardSetScreenModule {
    @Binds
    abstract fun bindDeleteSetUseCase(
        deleteSetUseCaseImpl: DeleteSetUseCaseImpl
    ) : DeleteSetUseCase

    @Binds
    abstract fun bindGetAllSetsUseCase(
        getAllSetsUseCaseImpl: GetAllSetsUseCaseImpl
    ) : GetAllSetsUseCase

    @Binds
    abstract fun bindInsertSetUseCase(
        insertSetUseCaseImpl: InsertSetUseCaseImpl
    ) : InsertSetUseCase

    @Binds
    abstract fun bindUpdateSetUseCase(
        updateSetUseCaseImpl: UpdateSetUseCaseImpl
    ) : UpdateSetUseCase
}