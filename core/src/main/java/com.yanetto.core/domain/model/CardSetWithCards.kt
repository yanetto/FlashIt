package com.yanetto.core.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class CardSetWithCards(
    @Embedded val cardSet: com.yanetto.core.data.source.local.model.CardSetEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "setId"
    )
    val cards: List<com.yanetto.core.data.source.local.model.CardEntity>
)