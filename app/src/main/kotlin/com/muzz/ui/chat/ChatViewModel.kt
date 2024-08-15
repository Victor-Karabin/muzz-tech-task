package com.muzz.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.common.Ticker
import com.muzz.data.chat.MessageRepo
import com.muzz.domain.Message
import com.muzz.ui.chat.mappers.toItems
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    @Assisted("user1") private val user1: String,
    @Assisted("user2") private val user2: String,
    private val messageRepo: MessageRepo,
    private val ticker: Ticker
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("user1") user1: String,
            @Assisted("user2") user2: String
        ): ChatViewModel
    }

    private val unknownErrorChannel = Channel<Throwable>(capacity = Channel.BUFFERED)
    internal val unknownError: Flow<Throwable>
        get() = unknownErrorChannel.receiveAsFlow()

    private val mutableActiveUser = MutableStateFlow(user1)
    internal val activeUser = mutableActiveUser.asStateFlow()

    private val mutableUserInput = MutableStateFlow("")
    internal val userInput = mutableUserInput.asStateFlow()

    internal val enableSend = userInput.map { message -> message.isNotBlank() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = false)

    internal val items = messageRepo.subscribe().combine(activeUser) { messages, activeUser ->
        messages.toItems(TIME_ITEM_DELAY, FAST_MESSAGE_DELAY, activeUser, ticker.currentTimezone())
            .toImmutableList()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = persistentListOf())

    internal fun switchUser() {
        mutableActiveUser.value = if (activeUser.value == user1) user2 else user1

        mutableUserInput.value = ""
    }

    internal fun userInput(value: String) {
        mutableUserInput.value = value
    }

    internal fun clickSend() {
        viewModelScope.launch {
            val message = Message(
                id = "",
                authorId = activeUser.value,
                dateTime = ticker.currentDateTime(),
                message = userInput.value.trim()
            )

            messageRepo.store(message)
                .onSuccess { mutableUserInput.value = "" }
                .onFailure { ex ->
                    Log.d(TAG, "save message: $message failed", ex)
                    unknownErrorChannel.send(ex)
                }
        }
    }

    private companion object {
        private val TAG = ChatViewModel::class.java.name
        private val TIME_ITEM_DELAY = 1.toDuration(DurationUnit.HOURS)
        private val FAST_MESSAGE_DELAY = 20.toDuration(DurationUnit.SECONDS)
    }
}
