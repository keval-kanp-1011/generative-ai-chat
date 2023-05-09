package dev.kevalkanpariya.generativeaichat.models

import dev.kevalkanpariya.generativeaichat.MessageResponse

data class OpenAIResponse(
    val choices: List<MessageResponse>
)