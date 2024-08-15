package com.muzz.ui.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.muzz.ui.chat.mappers.toDayOfWeekText
import com.muzz.ui.chat.mappers.toTimeText
import com.muzz.ui.chat.models.ChatItem
import com.muzz.ui.theme.MuzzTheme
import kotlinx.datetime.LocalDateTime
import java.util.UUID

@Composable
internal fun DateTimeItem(
    item: ChatItem.DateTime,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(item.dateTime.toDayOfWeekText())
                }
                append(" ")
                append(item.dateTime.toTimeText())
            },
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
private fun PreviewDateTimeItemLight() {
    val item = ChatItem.DateTime(
        id = UUID.randomUUID().toString(),
        dateTime = LocalDateTime.parse("2024-08-15T14:09:01")
    )

    MuzzTheme(darkTheme = false) {
        DateTimeItem(item = item)
    }
}