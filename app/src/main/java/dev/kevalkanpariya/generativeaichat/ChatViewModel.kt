package dev.kevalkanpariya.generativeaichat

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kevalkanpariya.generativeaichat.data.OpenApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatViewModel: ViewModel() {
    val messages = mutableStateListOf<Message>()

    fun sendMessage(text: String, isUser: Boolean = true) {
        messages.add(Message(text, "user"))
        if (isUser) {
            viewModelScope.launch {
                val response = ApiService.openAIApi.generateResponse(OpenAIRequestBody(messages = messages))
                messages.add(response.choices.first().message)
            }
        }
    }
}

data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}


object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val openAIApi: OpenApi = retrofit.create(OpenApi::class.java)
}