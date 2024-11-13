package com.yanetto.flashit.data.source.local

import com.yanetto.flashit.domain.model.Card
import com.yanetto.flashit.domain.model.CardSet
import com.yanetto.flashit.domain.model.CardSetWithCards
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    //Card
    suspend fun insertCard(card: Card)

    suspend fun updateCard(card: Card)

    suspend fun deleteCard(card: Card)

    fun getCardByIdFlow(id: Int): Flow<Card>

    fun getCardSetWithCards(setId: Int): Flow<CardSetWithCards>

    //CardSet
    suspend fun insertCardSet(cardSet: CardSet)

    suspend fun updateCardSet(cardSet: CardSet)

    suspend fun deleteCardSet(cardSet: CardSet)

    fun getCardSetByIdFlow(id: Int): Flow<CardSet>

    fun getAllCardSetsFlow(): Flow<List<CardSet>>
}