package com.yanetto.core.data.source.local

import com.yanetto.core.data.mapper.toCard
import com.yanetto.core.data.mapper.toCardEntity
import com.yanetto.core.data.mapper.toCardSet
import com.yanetto.core.data.mapper.toCardSetEntity
import com.yanetto.core.data.source.local.dao.CardDao
import com.yanetto.core.data.source.local.dao.CardSetDao
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.model.CardSet
import com.yanetto.core.domain.model.CardSetWithCards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val cardDao: CardDao,
    private val cardSetDao: CardSetDao
) : LocalDataSource {
    //Card
    override suspend fun insertCard(card: Card) {
        cardDao.insertCard(card.toCardEntity())
    }

    override suspend fun updateCard(card: Card) {
        cardDao.updateCard(card.toCardEntity())
    }

    override suspend fun deleteCard(card: Card) {
        cardDao.deleteCard(card.toCardEntity())
    }

    override fun getCardByIdFlow(id: Int): Flow<Card> {
        return cardDao.getCardById(id).map { cardEntity ->
            cardEntity.toCard()
        }
    }

    override fun getCardSetWithCards(setId: Int): Flow<CardSetWithCards?> {
        return cardDao.getCardSetWithCards(setId)
    }

    //CardSet
    override suspend fun insertCardSet(cardSet: CardSet) {
        cardSetDao.insertCardSet(cardSet.toCardSetEntity())
    }

    override suspend fun updateCardSet(cardSet: CardSet) {
        cardSetDao.updateCardSet(cardSet.toCardSetEntity())
    }

    override suspend fun deleteCardSet(cardSet: CardSet) {
        cardSetDao.deleteCardSet(cardSet.toCardSetEntity())
    }

    override suspend fun deleteCardsBySetId(setId: Int) {
        cardSetDao.deleteCardsBySetId(setId)
    }

    override suspend fun deleteCardSetWithCards(cardSet: CardSet) {
        cardSetDao.deleteCardSetWithCards(cardSet.toCardSetEntity())
    }

    override fun getCardSetByIdFlow(id: Int): Flow<CardSet?> {
        return cardSetDao.getCardSetById(id).map { cardSetEntity ->
            cardSetEntity.toCardSet()
        }
    }

    override fun getAllCardSetsFlow(): Flow<List<CardSet>> {
        return cardSetDao.getAllCardSets().map { cardSetEntityList ->
            cardSetEntityList.map { cardSetEntity ->
                cardSetEntity.toCardSet()
            }
        }
    }
}