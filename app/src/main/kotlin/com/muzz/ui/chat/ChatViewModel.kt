package com.muzz.ui.chat

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    @Assisted("user1") private val user1: String,
    @Assisted("user2") private val user2: String,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("user1") user1: String,
            @Assisted("user2") user2: String
        ): ChatViewModel
    }

    private val mutableActiveUser = MutableStateFlow(user1)
    internal val activeUser = mutableActiveUser.asStateFlow()

    private val mutableUserInput = MutableStateFlow("")
    internal val userInput = mutableUserInput.asStateFlow()

    internal fun switchUser() {
        mutableActiveUser.value = if (activeUser.value == user1) user2 else user1

        mutableUserInput.value = ""
    }

    internal fun userInput(value: String) {
        mutableUserInput.value = value
    }
}
