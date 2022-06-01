package com.example.mvi

import app.cash.turbine.test
import com.example.mvi.ui.MainAction
import com.example.mvi.ui.MainEvent
import com.example.mvi.ui.MainState
import com.example.mvi.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Ichvandi
 * Created on 30/05/2022 at 21:08.
 */
@ExperimentalCoroutinesApi
class MainViewModelTest : BaseTest() {

    private lateinit var viewModel: MainViewModel

    override fun setUp() {
        super.setUp()
        viewModel = MainViewModel()
    }

    @Test
    fun TestFirstAction1() = runTest {
        // When
        viewModel.setAction(MainAction.FirstAction("admin"))

        // Then
        viewModel.state.test {
            assertEquals(
                MainState.FirstState("Admin"),
                awaitItem()
            )
        }

        viewModel.event.test {
            assertEquals(
                MainEvent.FirstEvent,
                awaitItem()
            )
        }
    }

    @Test
    fun TestFirstAction2() = runTest {
        // When
        viewModel.setAction(MainAction.FirstAction("user"))

        // Then
        viewModel.state.test {
            assertEquals(
                MainState.FirstState("User"),
                awaitItem()
            )
        }

        viewModel.event.test {
            assertEquals(
                MainEvent.FirstEvent,
                awaitItem()
            )
        }
    }

    @Test
    fun TestSecondAction() = runTest {
        // When
        viewModel.setAction(MainAction.SecondAction)

        // Then
        viewModel.state.test {
            assertEquals(
                MainState.SecondState,
                awaitItem()
            )
        }

        viewModel.event.test {
            assertEquals(
                MainEvent.SecondEvent,
                awaitItem()
            )
        }
    }

}