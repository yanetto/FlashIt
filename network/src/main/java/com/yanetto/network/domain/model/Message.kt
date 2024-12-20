package com.yanetto.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String,
    val text: String
)