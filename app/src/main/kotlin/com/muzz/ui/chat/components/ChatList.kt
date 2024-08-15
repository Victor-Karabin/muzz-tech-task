package com.muzz.ui.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.ui.chat.models.ChatItem
import com.muzz.ui.theme.MuzzTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDateTime
import java.util.UUID

@Composable
internal fun ChatList(
    items: ImmutableList<ChatItem>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state,
        verticalArrangement = Arrangement.Bottom
    ) {
        items(count = items.size, key = { index: Int -> items[index].id }) { index: Int ->
            when (val item = items[index]) {
                is ChatItem.DateTime -> DateTimeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    item = item
                )

                is ChatItem.Message -> MessageItemRow(
                    modifier = Modifier.fillMaxWidth(),
                    item = item
                )

                is ChatItem.LargeDivider -> Spacer(modifier = Modifier.height(24.dp))

                is ChatItem.SmallDivider -> Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewChatListLight() {
    val items = persistentListOf(
        ChatItem.DateTime(
            id = UUID.randomUUID().toString(),
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "Hello!"
        ),
        ChatItem.SmallDivider(
            id = UUID.randomUUID().toString()
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "What are you up to today?"
        ),
        ChatItem.LargeDivider(
            id = UUID.randomUUID().toString()
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = false,
            message = "Nothing much"
        )
    )

    MuzzTheme(darkTheme = false) {
        ChatList(items = items)
    }
}

@Preview
@Composable
private fun PreviewChatListDark() {
    val items = persistentListOf(
        ChatItem.DateTime(
            id = UUID.randomUUID().toString(),
            dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "Hello!"
        ),
        ChatItem.SmallDivider(
            id = UUID.randomUUID().toString()
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = true,
            message = "What are you up to today?"
        ),
        ChatItem.LargeDivider(
            id = UUID.randomUUID().toString()
        ),
        ChatItem.Message(
            id = UUID.randomUUID().toString(),
            isActiveUser = false,
            message = "Nothing much"
        )
    )

    MuzzTheme(darkTheme = true) {
        ChatList(items = items)
    }
}