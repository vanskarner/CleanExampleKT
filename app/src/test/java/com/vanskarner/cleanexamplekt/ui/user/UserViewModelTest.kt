package com.vanskarner.cleanexamplekt.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import com.vanskarner.cleanexamplekt.util.MainCoroutineRule
import com.vanskarner.user.UserComponent
import com.vanskarner.user.businesslogic.UserData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyList
import org.mockito.Mockito.`when`
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var errorFilter: ErrorFilter
    private lateinit var userComponent: UserComponent
    private lateinit var viewModel: UserViewModel

    @Before
    fun setupClas() {
        userComponent = mock()
        errorFilter = mock()
        viewModel = UserViewModel(userComponent, errorFilter)
    }

    @After
    fun tearDown() {
        clearInvocations(userComponent, errorFilter)
    }

    @Test
    fun `saveUser when is Success should show msg user added`() = runTest {
        val observerAddedUserMsg = mock<Observer<Unit>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.msgUserAdded.observeForever(observerAddedUserMsg)
        viewModel.progress.observeForever(observerProgress)
        val userExample = UserData(
            name = "Luis",
            age = 32
        )
        `when`(userComponent.save(userExample)).thenReturn(Result.success(Unit))

        viewModel.saveUser(userExample.name, userExample.age)
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).save(userExample)
        verify(observerProgress).onChanged(false)
        verify(observerAddedUserMsg).onChanged(Unit)
    }

    @Test
    fun `saveUser when is Failure should show error msg`() = runTest {
        val observerErrorMsg = mock<Observer<String>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.msgError.observeForever(observerErrorMsg)
        viewModel.progress.observeForever(observerProgress)
        val userExample = UserData(
            name = "Luis",
            age = 32
        )
        val exceptionExample = RuntimeException("Some Exception")
        `when`(errorFilter.filter(exceptionExample)).thenReturn(exceptionExample.message)
        `when`(userComponent.save(userExample)).thenReturn(Result.failure(exceptionExample))

        viewModel.saveUser(userExample.name, userExample.age)
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).save(userExample)
        verify(observerProgress).onChanged(false)
        verify(observerErrorMsg).onChanged(exceptionExample.message ?: "")
    }

    @Test
    fun `userList when is Success should show list`() = runTest {
        val observerUserList = mock<Observer<List<UserModel>>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.userList.observeForever(observerUserList)
        viewModel.progress.observeForever(observerProgress)
        `when`(userComponent.getList()).thenReturn(Result.success(anyList()))

        viewModel.userList()
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).getList()
        verify(observerProgress).onChanged(false)
        verify(observerUserList).onChanged(anyList())
    }

    @Test
    fun `userList when is Failure should show error msg`() = runTest {
        val observerErrorMsg = mock<Observer<String>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.msgError.observeForever(observerErrorMsg)
        viewModel.progress.observeForever(observerProgress)
        val exceptionExample = RuntimeException("Some Exception")
        `when`(errorFilter.filter(exceptionExample)).thenReturn(exceptionExample.message)
        `when`(userComponent.getList()).thenReturn(Result.failure(exceptionExample))

        viewModel.userList()
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).getList()
        verify(observerProgress).onChanged(false)
        verify(observerErrorMsg).onChanged(exceptionExample.message ?: "")
    }

    @Test
    fun `findUser when is Success should show user`() = runTest {
        val observerUser = mock<Observer<UserModel>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.user.observeForever(observerUser)
        viewModel.progress.observeForever(observerProgress)
        val userExample = UserData(1, "Luis", 32)
        `when`(userComponent.find(userExample.userId)).thenReturn(Result.success(userExample))

        viewModel.findUser(userExample.userId)
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).find(userExample.userId)
        verify(observerProgress).onChanged(false)
        verify(observerUser).onChanged(userExample.toModel())
    }

    @Test
    fun `findUser when is Failure should show error`() = runTest {
        val observerErrorMsg = mock<Observer<String>>()
        val observerProgress = mock<Observer<Boolean>>()
        viewModel.msgError.observeForever(observerErrorMsg)
        viewModel.progress.observeForever(observerProgress)
        val userExample = UserData(1, "Luis", 32)
        val exceptionExample = RuntimeException("Some exception")
        `when`(errorFilter.filter(exceptionExample)).thenReturn(exceptionExample.message)
        `when`(userComponent.find(userExample.userId)).thenReturn(Result.failure(exceptionExample))

        viewModel.findUser(userExample.userId)
        advanceUntilIdle()

        verify(observerProgress).onChanged(true)
        verify(userComponent).find(userExample.userId)
        verify(observerProgress).onChanged(false)
        verify(observerErrorMsg).onChanged(exceptionExample.message ?: "")
    }

}