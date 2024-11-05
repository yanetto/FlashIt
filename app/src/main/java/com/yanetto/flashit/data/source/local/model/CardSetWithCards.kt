package com.yanetto.flashit.data.source.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CardSetWithCards(
    @Embedded val cardSet: CardSetEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "setId"
    )
    val cards: List<CardEntity>
)