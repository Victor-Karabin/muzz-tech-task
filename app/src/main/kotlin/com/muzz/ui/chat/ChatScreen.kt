package com.muzz.ui.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.muzz.R

@Composable
internal fun ChatScreen(
    viewModel: ChatViewModel,
    close: () -> Unit,
    modifier: Modifier = Modifier
) {
    ChatScreen(modifier = modifier)
}

@Composable
private fun ChatScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.app_name))
    }
}

@Preview
@Composable
private fun PreviewChatScreen() {
    ChatScreen(
        modifier = Modifier.fillMaxSize()
    )
}