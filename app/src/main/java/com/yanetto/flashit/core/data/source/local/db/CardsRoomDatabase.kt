package com.yanetto.flashit.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yanetto.flashit.core.data.source.local.dao.CardDao
import com.yanetto.flashit.core.data.source.local.dao.CardSetDao
import com.yanetto.flashit.core.data.source.local.model.CardEntity
import com.yanetto.flashit.core.data.source.local.model.CardSetEntity

@Database(entities = [CardEntity::class, CardSetEntity::class], version = 1)
abstract class CardsRoomDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun cardSetDao(): CardSetDao
}