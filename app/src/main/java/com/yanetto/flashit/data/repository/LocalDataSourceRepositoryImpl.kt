package com.yanetto.flashit.data.repository

import com.yanetto.flashit.data.source.local.LocalDataSource
import com.yanetto.flashit.domain.model.Card
import com.yanetto.flashit.domain.model.CardSet
import com.yanetto.flashit.domain.model.CardSetWithCards
import com.yanetto.flashit.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val localDatasource: LocalDataSource
) : LocalDataSourceRepository {
    override suspend fun insertCard(card: Card) = localDatasource.insertCard(card)

    override suspend fun updateCard(card: Card) = localDatasource.updateCard(card)

    override suspend fun deleteCard(card: Card) = localDatasource.deleteCard(card)

    override fun getCardByIdFlow(id: Int): Flow<Card> = localDatasource.getCardByIdFlow(id)

    override fun getCardSetWithCards(setId: Int): Flow<CardSetWithCards> = localDatasource.getCardSetWithCards(setId)

    override suspend fun insertCardSet(cardSet: CardSet) = localDatasource.insertCardSet(cardSet)

    override suspend fun updateCardSet(cardSet: CardSet) = localDatasource.updateCardSet(cardSet)

    override suspend fun deleteCardSet(cardSet: CardSet) = localDatasource.deleteCardSet(cardSet)

    override fun getCardSetByIdFlow(id: Int): Flow<CardSet> = localDatasource.getCardSetByIdFlow(id)

    override fun getAllCardSetsFlow(): Flow<List<CardSet>> = localDatasource.getAllCardSetsFlow()
}