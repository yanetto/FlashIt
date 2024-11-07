package com.yanetto.flashit.data.mapper

import com.yanetto.flashit.data.source.local.model.CardEntity
import com.yanetto.flashit.data.source.local.model.CardSetEntity
import com.yanetto.flashit.domain.model.Card
import com.yanetto.flashit.domain.model.CardSet

fun CardEntity.toCard() = Card(id, question, answer, setId)
fun Card.toCardEntity() = CardEntity(id, question, answer, setId)

fun CardSetEntity.toCardSet() = CardSet(id, name)
fun CardSet.toCardSetEntity() = CardSetEntity(id, name)