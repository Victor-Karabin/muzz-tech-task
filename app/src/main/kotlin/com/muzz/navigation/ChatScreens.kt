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

internal fun ChatScreen.toRoute(): String {
    return this.toPath() + when (this) {
        ChatScreen.Chat -> ""
    }
}

internal fun NavGraphBuilder.chatNavGraph(navController: NavController) {
    composable(route = ChatScreen.Chat.toRoute()) {
        val viewModel = hiltViewModel<ChatViewModel>()

        ChatScreen(
            modifier = Modifier.fillMaxSize(),
            viewModel = viewModel,
            close = { navController.popBackStack() }
        )
    }
}