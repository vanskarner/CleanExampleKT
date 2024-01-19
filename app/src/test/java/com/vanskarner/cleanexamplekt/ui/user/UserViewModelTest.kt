package com.vanskarner.cleanexamplekt.ui.user

import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import com.vanskarner.user.UserComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.clearInvocations
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class UserViewModelTest {
    private lateinit var errorFilter: ErrorFilter
    private lateinit var userComponent: UserComponent
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        userComponent = mock(UserComponent::class.java)
        errorFilter = mock(ErrorFilter::class.java)
        viewModel = UserViewModel(userComponent, errorFilter)
    }

    @After
    fun tearDown() {
        clearInvocations(userComponent, errorFilter)
    }

    @Test
    fun saveUser_whenIsOk_showAddedUserMsg() = runTest {

    }

}