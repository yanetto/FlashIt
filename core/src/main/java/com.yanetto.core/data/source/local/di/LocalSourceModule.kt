package com.yanetto.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.yanetto.core.data.repository.LocalDataSourceRepositoryImpl
import com.yanetto.core.data.source.local.LocalDataSource
import com.yanetto.core.data.source.local.RoomLocalDataSource
import com.yanetto.core.data.source.local.db.CardsRoomDatabase
import com.yanetto.core.domain.repository.LocalDataSourceRepository
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
    fun provideLocalDatabase(
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
    abstract fun bindDefaultFlashItRepository(
        defaultFlashItRepository: LocalDataSourceRepositoryImpl
    ) : LocalDataSourceRepository
}