package com.muzz.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.ui.theme.MuzzTheme

private val activeShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 12.dp)
private val inActiveShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomEnd = 12.dp)

@Composable
internal fun MessageItem(
    text: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = if (isActive) activeShape else inActiveShape

    val backgroundColor = if (isActive) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val textColor = if (isActive) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor, shape)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun PreviewMessageItemActiveLight() {
    MuzzTheme(darkTheme = false) {
        MessageItem(
            text = "Does 7pm work for you?",
            isActive = true
        )
    }
}

@Preview
@Composable
private fun PreviewMessageItemInActiveLight() {
    MuzzTheme(darkTheme = false) {
        MessageItem(
            text = "Does 7pm work for you?",
            isActive = false
        )
    }
}

@Preview
@Composable
private fun PreviewMessageItemActiveDark() {
    MuzzTheme(darkTheme = true) {
        MessageItem(
            text = "Does 7pm work for you?",
            isActive = true
        )
    }
}

@Preview
@Composable
private fun PreviewMessageItemInActiveDark() {
    MuzzTheme(darkTheme = true) {
        MessageItem(
            text = "Does 7pm work for you?",
            isActive = false
        )
    }
}