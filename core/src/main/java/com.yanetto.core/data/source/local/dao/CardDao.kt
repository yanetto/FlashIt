package com.yanetto.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yanetto.core.data.source.local.model.CardEntity
import com.yanetto.core.domain.model.CardSetWithCards
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

    @Transaction
    @Query("SELECT * FROM CardSetEntity WHERE id = :setId")
    fun getCardSetWithCards(setId: Int): Flow<CardSetWithCards?>
}