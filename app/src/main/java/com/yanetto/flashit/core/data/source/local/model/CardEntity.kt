package com.yanetto.flashit.core.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = CardSetEntity::class,
        parentColumns = ["id"],
        childColumns = ["setId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("setId")]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val answer: String,
    val setId: Int
)