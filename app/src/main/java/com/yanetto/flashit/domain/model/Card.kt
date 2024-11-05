package com.yanetto.flashit.domain.model

data class Card(
    val id: Int,
    val question: String,
    val answer: String,
    val setId: Int
)