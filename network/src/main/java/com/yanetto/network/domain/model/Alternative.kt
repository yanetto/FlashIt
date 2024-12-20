package com.yanetto.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Alternative(
    val message: Message,
    val status: String
)