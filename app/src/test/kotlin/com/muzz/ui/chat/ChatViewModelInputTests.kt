package com.muzz.ui.chat

import com.muzz.common.MainCoroutineRule
import com.muzz.ui.chat.mock.ChatViewModelMockFactory
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ChatViewModelInputTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val viewModel by lazy { ChatViewModelMockFactory.create() }

    @Test
    fun `given users when initial state then user input is empty`() {
        assertEquals("", viewModel.userInput.value)
    }

    @Test
    fun `given initial state when user type then user input is saved`() {
        val message = "Hello"

        viewModel.userInput(message)
        assertEquals(message, viewModel.userInput.value)
    }

    @Test
    fun `given user input when switch user then user input is cleared`() {
        val message = "Hello"
        viewModel.userInput(message)

        viewModel.switchUser()
        assertEquals("", viewModel.userInput.value)
    }

    companion object {
        private const val USER_1 = "Sarah"
        private const val USER_2 = "Andy"
    }
}