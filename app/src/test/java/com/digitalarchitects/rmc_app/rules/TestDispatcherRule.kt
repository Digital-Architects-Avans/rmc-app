package com.digitalarchitects.rmc_app.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/* Since the Main dispatcher is only available in a UI context, you must replace it with a
 * unit-test-friendly dispatcher. The Kotlin Coroutines library provides a coroutine dispatcher for
 * this purpose called TestDispatcher. The TestDispatcher needs to be used instead of the Main
 * dispatcher for any unit test in which a new coroutine is made, as is the case with the
 * getUsers() function from the view model.
 */

class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}