package com.yanetto.flashit.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yanetto.flashit.core.data.source.local.model.CardSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardSetDao {
    @Insert
    fun insertCardSet(cardSet: CardSetEntity)

    @Update
    fun updateCardSet(cardSet: CardSetEntity)

    @Delete
    fun deleteCardSet(cardSet: CardSetEntity)

    @Query("DELETE FROM cardentity WHERE setId = :setId")
    fun deleteCardsBySetId(setId: Int)

    @Transaction
    fun deleteCardSetWithCards(cardSet: CardSetEntity) {
        deleteCardsBySetId(cardSet.id)
        deleteCardSet(cardSet)
    }

    @Query("SELECT * FROM cardsetentity WHERE id = :id")
    fun getCardSetById(id: Int): Flow<CardSetEntity>

    @Query("SELECT * FROM cardsetentity")
    fun getAllCardSets(): Flow<List<CardSetEntity>>
}