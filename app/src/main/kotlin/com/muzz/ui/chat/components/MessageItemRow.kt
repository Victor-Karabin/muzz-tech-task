package com.muzz.ui.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muzz.ui.chat.models.ChatItem
import com.muzz.ui.theme.MuzzTheme
import java.util.UUID

@Composable
internal fun MessageItemRow(
    item: ChatItem.Message,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = if (item.isActiveUser) Arrangement.End else Arrangement.Start
    ) {
        MessageItem(
            text = item.message,
            isActive = item.isActiveUser
        )
    }
}


@Preview
@Composable
private fun PreviewMessageItemRowActiveLight() {
    val item = ChatItem.Message(
        id = UUID.randomUUID().toString(),
        message = "Does 7pm work for you? I've got to go pick up my little brother first",
        isActiveUser = true
    )
    MuzzTheme(darkTheme = false) {
        MessageItemRow(
            modifier = Modifier.fillMaxWidth(),
            item = item
        )
    }
}

@Preview
@Composable
private fun PreviewMessageItemRowInActiveLight() {
    val item = ChatItem.Message(
        id = UUID.randomUUID().toString(),
        message = "Does 7pm work for you? I've got to go pick up my little brother first",
        isActiveUser = false
    )
    MuzzTheme(darkTheme = false) {
        MessageItemRow(
            modifier = Modifier.fillMaxWidth(),
            item = item
        )
    }
}


@Preview
@Composable
private fun PreviewMessageItemRowActiveDark() {
    val item = ChatItem.Message(
        id = UUID.randomUUID().toString(),
        message = "Does 7pm work for you? I've got to go pick up my little brother first",
        isActiveUser = true
    )
    MuzzTheme(darkTheme = true) {
        MessageItemRow(
            modifier = Modifier.fillMaxWidth(),
            item = item
        )
    }
}


@Preview
@Composable
private fun PreviewMessageItemRowInActiveDark() {
    val item = ChatItem.Message(
        id = UUID.randomUUID().toString(),
        message = "Does 7pm work for you? I've got to go pick up my little brother first",
        isActiveUser = false
    )
    MuzzTheme(darkTheme = true) {
        MessageItemRow(
            modifier = Modifier.fillMaxWidth(),
            item = item
        )
    }
}