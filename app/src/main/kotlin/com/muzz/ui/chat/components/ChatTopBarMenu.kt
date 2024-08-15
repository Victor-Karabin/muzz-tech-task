package com.muzz.ui.chat.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.muzz.R
import com.muzz.ui.theme.MuzzTheme

@Composable
internal fun ChatTopBarMenu(
    menuExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onClickSwitchUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        modifier = modifier,
        expanded = menuExpanded,
        onDismissRequest = onDismissRequest,
    ) {
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.chat_switch_users))
            },
            onClick = onClickSwitchUser,
        )
    }
}

@Preview
@Composable
private fun PreviewChatTopBarMenuLight() {
    MuzzTheme(darkTheme = false) {
        ChatTopBarMenu(
            menuExpanded = true,
            onDismissRequest = {},
            onClickSwitchUser = {}
        )
    }
}

@Preview
@Composable
private fun PreviewChatTopBarMenuDark() {
    MuzzTheme(darkTheme = true) {
        ChatTopBarMenu(
            menuExpanded = true,
            onDismissRequest = {},
            onClickSwitchUser = {}
        )
    }
}