package com.vanskarner.user.persistence

import com.vanskarner.user.businesslogic.UserData
import org.junit.Test
import org.junit.Assert.*

class UserMapperTest {

    @Test
    fun `toDTO from UserData`() {
        val expected = UserData(
            userId = 1,
            name = "Luis",
            age = 32
        )
        val actual = expected.toDTO()

        assertEquals(expected.userId, actual.id)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.age, actual.age)
    }

    @Test
    fun `toData from UserDTO`() {
        val expected = UserDTO(
            id = 1,
            name = "Luis",
            age = 32
        )
        val actual = expected.toData()

        assertEquals(expected.id, actual.userId)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.age, actual.age)
    }

}