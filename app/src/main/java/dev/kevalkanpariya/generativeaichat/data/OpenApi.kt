package dev.kevalkanpariya.generativeaichat.data

import dev.kevalkanpariya.generativeaichat.models.OpenAIRequestBody
import dev.kevalkanpariya.generativeaichat.models.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenApi {
    @Headers("Content-Type: application/json","Authorization: Bearer API-KEY")
    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}

