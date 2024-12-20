package com.yanetto.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CompletionOptions(
    val stream: Boolean,
    val temperature: Double,
    val maxTokens: String
)