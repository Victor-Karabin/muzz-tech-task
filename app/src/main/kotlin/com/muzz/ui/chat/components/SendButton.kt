package com.muzz.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.R
import com.muzz.ui.theme.Palette

@Composable
internal fun SendButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(Palette.sendGradient),
                    shape = CircleShape
                ),
            onClick = onClick,
            enabled = enabled
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Send,
                contentDescription = stringResource(id = R.string.chat_send),
                tint = Palette.White
            )
        }

        if (!enabled) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Palette.White.copy(alpha = 0.4f))
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSendButtonEnabled() {
    SendButton(
        modifier = Modifier.size(48.dp),
        enabled = true,
        onClick = {}
    )
}

@Preview
@Composable
private fun PreviewSendButtonDisabled() {
    SendButton(
        modifier = Modifier.size(48.dp),
        enabled = false,
        onClick = {}
    )
}