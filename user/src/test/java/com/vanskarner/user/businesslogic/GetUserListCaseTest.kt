package com.vanskarner.user.businesslogic

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUserListCaseTest {
    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUserListUseCase

    @Before
    fun setUp() {
        repository = FakeUserRepository()
        useCase = GetUserListUseCase(repository)
    }

    @Test
    fun `execute without records should get empty list`(): Unit = runTest {
        val userList = useCase.execute().getOrThrow()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `execute with records should get the list`(): Unit = runTest {
        val userExpected = UserData(
            name = "Luis",
            age = 32
        )
        repository.save(userExpected)
        val userListActual = useCase.execute().getOrThrow()

        assertTrue(userListActual.isNotEmpty())
        assertEquals(1, userListActual.size)
        assertEquals(1, userListActual[0].userId)
        assertEquals(userExpected.name, userListActual[0].name)
        assertEquals(userExpected.age, userListActual[0].age)
    }

}