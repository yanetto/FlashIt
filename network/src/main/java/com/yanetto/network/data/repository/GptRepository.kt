package com.yanetto.network.data.repository

import android.util.Log
import com.yanetto.network.BuildConfig
import com.yanetto.network.domain.model.CompletionOptions
import com.yanetto.network.domain.model.Message
import com.yanetto.network.domain.model.Prompt
import com.yanetto.network.domain.model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class GptRepository @Inject constructor(private val client: OkHttpClient) {
    private val catalogId: String = BuildConfig.CATALOG_ID
    private val apiKey: String = BuildConfig.API_KEY
    private val baseUrl = "https://llm.api.cloud.yandex.net/foundationModels/v1/completion"

    suspend fun getGptResponse(question: String): String = withContext(Dispatchers.IO) {
        val prompt = Prompt(
            modelUri = "gpt://$catalogId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                stream = false,
                temperature = 0.6,
                maxTokens = "2000"
            ),
            messages = listOf(
                Message(
                    role = "system",
                    text = "Ты помогаешь мне писать краткие ответы на вопросы, чтобы записать их на карточках для обучения"
                ),
                Message(
                    role = "user",
                    text = question
                )
            )
        )

        val requestBody = Json.encodeToString<Prompt>(prompt).toRequestBody("application/json".toMediaType())
        Log.d("REQUEST_BODY", requestBody.toString())

        val request = Request.Builder()
            .url(baseUrl)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Api-Key $apiKey")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("Error: ${response.code} - ${response.message}")
            }
            val responseBody = response.body?.string()
            if (responseBody.isNullOrEmpty()) {
                throw Exception("Empty response body")
            }

            val responseString = Json.decodeFromString<Response>(responseBody)

            return@use responseString.result.alternatives.firstOrNull()?.message?.text ?: ""
        }
    }

}
