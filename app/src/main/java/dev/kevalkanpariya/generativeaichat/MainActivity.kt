package dev.kevalkanpariya.generativeaichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kevalkanpariya.generativeaichat.ui.theme.GenerativeAIChatTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerativeAIChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val chatViewModel = viewModel<ChatViewModel>()
                    ChatScreen(chatViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(viewModel.messages.reversed()) { message ->
                if (message.isUser) {
                    Message(message.content, Alignment.End)
                } else {
                    Message(message.content, Alignment.Start)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            var inputText by remember { mutableStateOf("") }
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(),
                label = { Text("Type a message") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    viewModel.sendMessage(inputText)
                    inputText = ""
                })
            )

            IconButton(
                onClick = {
                    viewModel.sendMessage(inputText)
                    inputText = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send message")
            }
        }
    }
}

@Composable
fun Message(text: String, alignment: Alignment.Horizontal) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .wrapContentWidth(alignment)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}