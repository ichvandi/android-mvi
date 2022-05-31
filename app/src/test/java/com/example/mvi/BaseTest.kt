package com.example.mvi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 21:30.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
abstract class BaseTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun cleanUp() {
        Dispatchers.resetMain()
    }

}