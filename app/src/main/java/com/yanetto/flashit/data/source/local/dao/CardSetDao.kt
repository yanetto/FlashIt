package com.yanetto.flashit.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yanetto.flashit.data.source.local.model.CardSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardSetDao {
    @Insert
    fun insertCardSet(cardSet: CardSetEntity)

    @Update
    fun updateCardSet(cardSet: CardSetEntity)

    @Delete
    fun deleteCardSet(cardSet: CardSetEntity)

    @Query("SELECT * FROM cardsetentity WHERE id = :id")
    fun getCardSetById(id: Int): Flow<CardSetEntity>

    @Query("SELECT * FROM cardsetentity")
    fun getAllCardSets(): Flow<List<CardSetEntity>>
}