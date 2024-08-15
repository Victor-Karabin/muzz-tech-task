package com.muzz.ui.chat.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.ui.theme.MuzzTheme

@Composable
internal fun ChatBottomBar(
    userInput: String,
    enabledSend: Boolean,
    onChangeUserInput: (String) -> Unit,
    onClickSend: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            shape = CircleShape,
            value = userInput,
            onValueChange = onChangeUserInput,
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(onSend = { if (enabledSend) onClickSend() }),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))

        SendButton(
            modifier = Modifier.size(48.dp),
            enabled = enabledSend,
            onClick = onClickSend
        )
    }
}

@Preview
@Composable
private fun PreviewChatBottomBarLight() {
    MuzzTheme(darkTheme = false) {
        ChatBottomBar(
            userInput = "",
            enabledSend = false,
            onChangeUserInput = {},
            onClickSend = {}
        )
    }
}

@Preview
@Composable
private fun PreviewChatBottomBarEnabledSendLight() {
    MuzzTheme(darkTheme = false) {
        ChatBottomBar(
            userInput = "Hello",
            enabledSend = true,
            onChangeUserInput = {},
            onClickSend = {}
        )
    }
}

@Preview
@Composable
private fun PreviewChatBottomBarDark() {
    MuzzTheme(darkTheme = true) {
        ChatBottomBar(
            userInput = "",
            enabledSend = false,
            onChangeUserInput = {},
            onClickSend = {}
        )
    }
}

@Preview
@Composable
private fun PreviewChatBottomBarEnabledSendDark() {
    MuzzTheme(darkTheme = true) {
        ChatBottomBar(
            userInput = "Hello",
            enabledSend = true,
            onChangeUserInput = {},
            onClickSend = {}
        )
    }
}