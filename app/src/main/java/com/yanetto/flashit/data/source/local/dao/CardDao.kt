package com.yanetto.flashit.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yanetto.flashit.data.source.local.model.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert
    fun insertCard(card: CardEntity)

    @Update
    fun updateCard(card: CardEntity)

    @Delete
    fun deleteCard(card: CardEntity)

    @Query("SELECT * FROM cardentity WHERE id = :id")
    fun getCardById(id: Int): Flow<CardEntity>
}