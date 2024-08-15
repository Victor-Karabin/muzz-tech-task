package com.muzz.ui.chat

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muzz.R
import com.muzz.ui.chat.components.ChatBottomBar
import com.muzz.ui.chat.components.ChatList
import com.muzz.ui.chat.components.ChatTopBar
import com.muzz.ui.chat.models.ChatItem
import com.muzz.ui.common.SingleEventEffect
import com.muzz.ui.theme.MuzzTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDateTime
import java.util.UUID

@Composable
internal fun ChatScreen(
    viewModel: ChatViewModel,
    close: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val unknownError = stringResource(id = R.string.common_error_occurred)

    SingleEventEffect(sideEffectFlow = viewModel.unknownError) {
        Toast.makeText(context, unknownError, Toast.LENGTH_LONG).show()
    }

    val userName by viewModel.activeUser.collectAsStateWithLifecycle()
    val userInput by viewModel.userInput.collectAsStateWithLifecycle()
    val enableSend by viewModel.enableSend.collectAsStateWithLifecycle()
    val items by viewModel.items.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current

    ChatScreen(
        modifier = modifier,
        userName = userName,
        items = items,
        userInput = userInput,
        enableSend = enableSend,
        onClickBack = {
            keyboardController?.hide()
            close()
        },
        onChangeInput = viewModel::userInput,
        onClickSend = viewModel::clickSend,
        onClickSwitchUser = viewModel::switchUser
    )
}

@Composable
private fun ChatScreen(
    userName: String,
    items: ImmutableList<ChatItem>,
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
        ChatList(
            modifier = Modifier.padding(padding),
            items = items,
            contentPadding = PaddingValues(all = 8.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewChatScreenLight() {
    val items = persistentListOf(
        ChatItem.DateTime(
            id = UUID.randomUUID().toString(),
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "What are you up to today?"
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = false,
            message = "Nothing much"
        )
    )

    MuzzTheme(darkTheme = false) {
        ChatScreen(
            userName = "Sarah",
            items = items,
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
    val items = persistentListOf(
        ChatItem.DateTime(
            id = UUID.randomUUID().toString(),
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "What are you up to today?"
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = false,
            message = "Nothing much"
        )
    )

    MuzzTheme(darkTheme = true) {
        ChatScreen(
            userName = "Sarah",
            items = items,
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