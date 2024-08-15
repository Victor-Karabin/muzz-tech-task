package com.muzz.ui.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muzz.ui.chat.components.ChatBottomBar
import com.muzz.ui.chat.components.ChatTopBar
import com.muzz.ui.theme.MuzzTheme

@Composable
internal fun ChatScreen(
    viewModel: ChatViewModel,
    close: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userName by viewModel.activeUser.collectAsStateWithLifecycle()
    val userInput by viewModel.userInput.collectAsStateWithLifecycle()
    val enableSend by viewModel.enableSend.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current

    ChatScreen(
        modifier = modifier,
        userName = userName,
        onClickBack = {
            keyboardController?.hide()
            close()
        },
        userInput = userInput,
        enableSend = enableSend,
        onChangeInput = viewModel::userInput,
        onClickSend = viewModel::clickSend,
        onClickSwitchUser = viewModel::switchUser
    )
}

@Composable
private fun ChatScreen(
    userName: String,
    userInput: String,
    enableSend: Boolean,
    onClickBack: () -> Unit,
    onChangeInput: (String) -> Unit,
    onClickSend: () -> Unit,
    onClickSwitchUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(shadowElevation = 6.dp) {
                var menuExpanded by remember { mutableStateOf(false) }

                ChatTopBar(
                    userName = userName,
                    menuExpanded = menuExpanded,
                    onClickBack = onClickBack,
                    onClickMore = { menuExpanded = !menuExpanded },
                    onDismissMenuRequest = { menuExpanded = false },
                    onClickSwitchUser = {
                        menuExpanded = false
                        onClickSwitchUser()
                    }
                )
            }
        },
        bottomBar = {
            Surface(shadowElevation = 18.dp) {
                ChatBottomBar(
                    modifier = Modifier.fillMaxWidth(),
                    userInput = userInput,
                    enabledSend = enableSend,
                    onChangeUserInput = onChangeInput,
                    onClickSend = onClickSend
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding))
    }
}

@Preview
@Composable
private fun PreviewChatScreenLight() {
    MuzzTheme(darkTheme = false) {
        ChatScreen(
            userName = "Sarah",
            userInput = "",
            enableSend = false,
            onClickBack = {},
            onChangeInput = {},
            onClickSend = {},
            onClickSwitchUser = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun PreviewChatScreenDark() {
    MuzzTheme(darkTheme = true) {
        ChatScreen(
            userName = "Sarah",
            userInput = "",
            enableSend = false,
            onClickBack = {},
            onChangeInput = {},
            onClickSend = {},
            onClickSwitchUser = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}