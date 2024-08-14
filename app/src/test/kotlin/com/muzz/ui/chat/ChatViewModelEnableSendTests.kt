package com.muzz.ui.chat

import com.muzz.common.MainCoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelEnableSendTests {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val viewModel by lazy { ChatViewModelMockFactory.create() }

    @Test
    fun `given users when initial state then send is disabled`() {
        assertEquals(false, viewModel.enableSend.value)
    }

    @Test
    fun `given initial state when user type message then send is enabled`() = runTest {
        viewModel.userInput("Hello")
        advanceUntilIdle()

        assertEquals(true, viewModel.enableSend.value)
    }

    @Test
    fun `given initial state when user type blank message then send is disabled`() = runTest {
        viewModel.userInput(" ")
        advanceUntilIdle()

        assertEquals(false, viewModel.enableSend.value)
    }

    @Test
    fun `given initial state when user clear message then send is disabled`() = runTest {
        viewModel.userInput("Hello")
        viewModel.userInput("")
        advanceUntilIdle()

        assertEquals(false, viewModel.enableSend.value)
    }
}