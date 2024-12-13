package com.yanetto.core.data.mapper

import com.yanetto.core.data.source.local.model.CardEntity
import com.yanetto.core.data.source.local.model.CardSetEntity
import com.yanetto.core.domain.model.Card
import com.yanetto.core.domain.model.CardSet

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