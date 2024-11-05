package com.yanetto.flashit.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yanetto.flashit.data.source.local.model.CardEntity
import com.yanetto.flashit.data.source.local.model.CardSetEntity

data class CardSetWithCards(
    @Embedded val cardSet: CardSetEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "setId"
    )
    val cards: List<CardEntity>
)