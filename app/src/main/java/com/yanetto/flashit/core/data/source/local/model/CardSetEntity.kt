package com.yanetto.flashit.core.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardSetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo val name: String
)