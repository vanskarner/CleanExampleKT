package com.vanskarner.user.businesslogic

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FindUserUseCaseTest {
    private lateinit var repository: UserRepository
    private lateinit var useCase: FindUserUseCase

    @Before
    fun setUp() {
        repository = FakeUserRepository()
        useCase = FindUserUseCase(repository)
    }

    @Test
    fun `execute without records should refer to NoSuchElementException`(): Unit = runTest {
        val exception = useCase.execute(5).exceptionOrNull()
        assertTrue(exception is NoSuchElementException)
    }

    @Test
    fun `run with records should get the user`(): Unit = runTest {
        val userExpected = UserData(
            name = "Luis",
            age = 32
        )
        repository.save(userExpected)
        val userActual = useCase.execute(1).getOrThrow()

        assertEquals(1, userActual.userId)
        assertEquals(userExpected.name, userActual.name)
        assertEquals(userExpected.age, userActual.age)
    }

}