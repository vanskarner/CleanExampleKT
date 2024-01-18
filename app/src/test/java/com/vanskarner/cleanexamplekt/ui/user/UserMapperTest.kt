package com.vanskarner.cleanexamplekt.ui.user

import com.vanskarner.user.businesslogic.UserData
import org.junit.Test
import org.junit.Assert.*

class UserMapperTest {

    @Test
    fun `toModel from UserData`() {
        val expected = UserData(
            name = "Luis",
            age = 32
        )
        val actual = expected.toModel()

        assertEquals(expected.userId, actual.id)
        assertEquals(expected.name, actual.name)
        assertEquals(expected.age, actual.age)
    }

}