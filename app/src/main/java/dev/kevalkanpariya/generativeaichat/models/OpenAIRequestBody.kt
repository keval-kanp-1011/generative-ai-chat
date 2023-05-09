package dev.kevalkanpariya.generativeaichat.models

import dev.kevalkanpariya.generativeaichat.Message

data class OpenAIRequestBody(
    val model: String = "gpt-3.5-turbo-0301",
    val messages: List<Message>,
    val max_tokens: Int = 50,
    val n: Int = 1,
    val temperature: Double = 1.0
)