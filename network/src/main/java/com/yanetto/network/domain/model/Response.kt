package com.yanetto.network.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val result: Result
)