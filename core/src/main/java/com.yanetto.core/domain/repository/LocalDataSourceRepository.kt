package com.yanetto.core.domain.repository

import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.model.CardSetWithCards
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {
    //Card
    suspend fun insertCard(card: Card)

    suspend fun updateCard(card: Card)

    suspend fun deleteCard(card: Card)

    fun getCardByIdFlow(id: Int): Flow<Card>

    fun getCardSetWithCards(setId: Int): Flow<CardSetWithCards?>

    //CardSet
    suspend fun insertCardSet(cardSet: CardSet)

    suspend fun updateCardSet(cardSet: CardSet)

    suspend fun deleteCardSet(cardSet: CardSet)

    suspend fun deleteCardsBySetId(setId: Int)

    suspend fun deleteCardSetWithCards(cardSet: CardSet)

    fun getCardSetByIdFlow(id: Int): Flow<CardSet?>

    fun getAllCardSetsFlow(): Flow<List<CardSet>>
}