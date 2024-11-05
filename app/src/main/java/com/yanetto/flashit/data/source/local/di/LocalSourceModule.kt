package com.yanetto.flashit.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.yanetto.flashit.data.repository.LocalDataSourceRepositoryImpl
import com.yanetto.flashit.data.source.local.LocalDataSource
import com.yanetto.flashit.data.source.local.RoomLocalDataSource
import com.yanetto.flashit.data.source.local.db.CardsRoomDatabase
import com.yanetto.flashit.domain.repository.LocalDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModuleProvider {

    @Provides
    fun provideCardDao(database: CardsRoomDatabase) = database.cardDao()
    @Provides
    fun provideCardSetDao(database: CardsRoomDatabase) = database.cardSetDao()

    @Provides
    @Singleton
    fun providesLocalDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CardsRoomDatabase::class.java,
        "flash_it-database"
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModuleBuilder {
    @Binds
    abstract fun bindRoomLocalDataSource(
        roomLocalDataSource: RoomLocalDataSource
    ) : LocalDataSource

    @Binds
    abstract fun bindDefaultJustNotesRepository(
        defaultJustNotesRepository: LocalDataSourceRepositoryImpl
    ) : LocalDataSourceRepository
}