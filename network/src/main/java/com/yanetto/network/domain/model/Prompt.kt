package com.yanetto.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Prompt(
    val modelUri: String,
    val completionOptions: CompletionOptions,
    val messages: List<Message>
)