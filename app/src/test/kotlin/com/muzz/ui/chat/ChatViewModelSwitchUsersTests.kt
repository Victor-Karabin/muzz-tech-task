package com.muzz.ui.chat

import com.muzz.common.MainCoroutineRule
import com.muzz.ui.chat.mock.ChatViewModelMockFactory
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ChatViewModelSwitchUsersTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val viewModel by lazy { ChatViewModelMockFactory.create(USER_1, USER_2) }

    @Test
    fun `given users when initial state then user1 is active`() {
        assertEquals(USER_1, viewModel.activeUser.value)
    }

    @Test
    fun `given initial state when switch then user2 is active`() {
        viewModel.switchUser()

        assertEquals(USER_2, viewModel.activeUser.value)
    }

    @Test
    fun `given initial state when double switch then user1 is active`() {
        viewModel.switchUser()
        viewModel.switchUser()

        assertEquals(USER_1, viewModel.activeUser.value)
    }

    companion object {
        private const val USER_1 = "Sarah"
        private const val USER_2 = "Andy"
    }
}