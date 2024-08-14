package com.muzz.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muzz.ui.chat.ChatScreen
import com.muzz.ui.chat.ChatViewModel

internal enum class ChatScreen {
    Chat
}

internal fun ChatScreen.toPath(): String {
    return when (this) {
        ChatScreen.Chat -> "chat"
    }
}

// can be extended with nav-params
internal fun ChatScreen.toRoute(): String {
    return this.toPath() + when (this) {
        ChatScreen.Chat -> ""
    }
}

internal fun NavGraphBuilder.chatNavGraph(navController: NavController) {
    composable(route = ChatScreen.Chat.toRoute()) {
        val user1 = "Sarah"
        val user2 = "Andy"

        val viewModel = hiltViewModel(
            key = ChatViewModel::class.java.name + "#$user1#$user2",
            creationCallback = { factory: ChatViewModel.Factory ->
                factory.create(user1, user2)
            }
        )

        ChatScreen(
            modifier = Modifier.fillMaxSize(),
            viewModel = viewModel,
            close = { navController.popBackStack() }
        )
    }
}