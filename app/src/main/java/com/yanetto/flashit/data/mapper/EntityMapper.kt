package com.yanetto.flashit.data.mapper

import com.yanetto.flashit.data.source.local.model.CardEntity
import com.yanetto.flashit.data.source.local.model.CardSetEntity
import com.yanetto.flashit.domain.model.Card
import com.yanetto.flashit.domain.model.CardSet

fun CardEntity?.toCard() = Card(
    this?.id ?: 0,
    this?.question ?: "",
    this?.answer ?: "",
    this?.setId ?: 0
)

fun Card?.toCardEntity() = CardEntity(
    this?.id ?: 0,
    this?.question ?: "",
    this?.answer ?: "",
    this?.setId ?: 0
)

fun CardSetEntity?.toCardSet() = CardSet(
    this?.id ?: 0,
    this?.name ?: ""
)

fun CardSet?.toCardSetEntity() = CardSetEntity(
    this?.id ?: 0,
    this?.name ?: ""
)