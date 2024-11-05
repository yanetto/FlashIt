package com.yanetto.flashit.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val answer: String
)