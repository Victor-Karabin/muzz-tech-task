package com.muzz.ui.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.R
import com.muzz.ui.theme.MuzzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatTopBar(
    userName: String,
    menuExpanded: Boolean,
    onClickBack: () -> Unit,
    onClickMore: () -> Unit,
    onDismissMenuRequest: () -> Unit,
    onClickSwitchUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.common_back),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val descr = userName + stringResource(id = R.string.common_belongs_photo)
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = descr
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(userName)
            }
        },
        actions = {
            IconButton(onClick = onClickMore) {
                Icon(
                    modifier = Modifier.rotate(90f),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.common_menu),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

            ChatTopBarMenu(
                menuExpanded = menuExpanded,
                onDismissRequest = onDismissMenuRequest,
                onClickSwitchUser = onClickSwitchUser
            )
        }
    )
}

@Preview
@Composable
private fun PreviewChatTopBarLight() {
    MuzzTheme(darkTheme = false) {
        ChatTopBar(
            userName = "Sarach",
            menuExpanded = false,
            onClickBack = {},
            onClickMore = {},
            onDismissMenuRequest = {},
            onClickSwitchUser = { }
        )
    }
}

@Preview
@Composable
private fun PreviewChatTopBarMenuLight() {
    MuzzTheme(darkTheme = false) {
        Box(modifier = Modifier.height(190.dp)) {
            ChatTopBar(
                userName = "Sarach",
                menuExpanded = true,
                onClickBack = {},
                onClickMore = {},
                onDismissMenuRequest = {},
                onClickSwitchUser = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewChatTopBarDark() {
    MuzzTheme(darkTheme = true) {
        ChatTopBar(
            userName = "Sarach",
            menuExpanded = false,
            onClickBack = {},
            onClickMore = {},
            onDismissMenuRequest = {},
            onClickSwitchUser = { }
        )
    }
}

@Preview
@Composable
private fun PreviewChatTopBarMenuDark() {
    MuzzTheme(darkTheme = true) {
        Box(modifier = Modifier.height(190.dp)) {
            ChatTopBar(
                userName = "Sarach",
                menuExpanded = true,
                onClickBack = {},
                onClickMore = {},
                onDismissMenuRequest = {},
                onClickSwitchUser = { }
            )
        }
    }
}