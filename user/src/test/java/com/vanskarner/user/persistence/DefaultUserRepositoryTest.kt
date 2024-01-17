package com.vanskarner.user.persistence

import com.vanskarner.user.businesslogic.UserData
import com.vanskarner.user.businesslogic.UserRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultUserRepositoryTest {
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = DefaultUserRepository()
    }

    @Test
    fun save_savedUser() = runTest {
        val expected = UserData(
            name = "Luis",
            age = 32
        )
        userRepository.save(expected).getOrThrow()
        val actual = userRepository.find(1).getOrThrow()

        assertEquals(1, actual.userId)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.age, actual.age)
    }

    @Test
    fun list_returnUserList() = runTest {
        val expected = UserData(
            name = "Luis",
            age = 32
        )
        userRepository.save(expected).getOrThrow()
        val actual = userRepository.list().getOrThrow()

        assertEquals(1, actual.size)
        assertEquals(1, actual[0].userId)
        assertEquals(expected.name, actual[0].name)
        assertEquals(expected.age, actual[0].age)
    }

    @Test
    fun `find with valid id should get user`() = runTest {
        val userId = 1
        val expected = UserData(
            name = "Luis",
            age = 32
        )
        userRepository.save(expected).getOrThrow()
        val actual = userRepository.find(userId).getOrThrow()

        assertEquals(userId, actual.userId)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.age, actual.age)
    }

    @Test
    fun `find with invalid id should refer to NotFound`() = runTest {
        val exception = userRepository.find(0).exceptionOrNull()

        assertTrue(exception is UserPersistenceError.NotFound)
    }

}