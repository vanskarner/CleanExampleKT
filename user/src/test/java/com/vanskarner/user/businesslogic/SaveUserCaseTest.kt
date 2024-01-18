package com.vanskarner.user.businesslogic

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SaveUserCaseTest {
    private lateinit var repository: UserRepository
    private lateinit var validateUserUseCase: ValidateUserUseCase
    private lateinit var useCase: SaveUserUseCase

    @Before
    fun setUp() {
        repository = FakeUserRepository()
        validateUserUseCase = ValidateUserUseCase()
        useCase = SaveUserUseCase(repository, validateUserUseCase)
    }

    @Test
    fun `execute with valid data should save it`(): Unit = runTest {
        val userExpected = UserData(
            name = "Luis",
            age = 32
        )
        useCase.execute(userExpected).getOrThrow()
        val userActual = repository.find(1).getOrThrow()

        assertEquals(1, userActual.userId)
        assertEquals(userExpected.name, userActual.name)
        assertEquals(userExpected.age, userActual.age)
    }

    @Test
    fun `execute with invalid name should get InvalidName`(): Unit = runTest {
        val userExpected = UserData(
            name = "",
            age = 32
        )
        val exception = useCase.execute(userExpected).exceptionOrNull()
        val invalidation = exception as UserBusinessLogicError.Invalidation

        assertEquals(1, invalidation.types.size)
        assertTrue(invalidation.types[0] is TypeInvalidation.InvalidName)
    }

    @Test
    fun `execute with invalid age should get InvalidAge`(): Unit = runTest {
        val userExpected = UserData(
            name = "Luis",
            age = 0
        )
        val exception = useCase.execute(userExpected).exceptionOrNull()
        val invalidation = exception as UserBusinessLogicError.Invalidation

        assertEquals(1, invalidation.types.size)
        assertTrue(invalidation.types[0] is TypeInvalidation.InvalidAge)
    }

    @Test
    fun `execute with invalid name and age should get InvalidName and InvalidAge`(): Unit =
        runTest {
            val userExpected = UserData(
                name = "",
                age = 0
            )
            val exception = useCase.execute(userExpected).exceptionOrNull()
            val invalidation = exception as UserBusinessLogicError.Invalidation

            assertEquals(2, invalidation.types.size)
            assertTrue(invalidation.types[0] is TypeInvalidation.InvalidName)
            assertTrue(invalidation.types[1] is TypeInvalidation.InvalidAge)
        }

}