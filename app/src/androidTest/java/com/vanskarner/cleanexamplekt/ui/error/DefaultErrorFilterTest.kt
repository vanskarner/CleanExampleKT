package com.vanskarner.cleanexamplekt.ui.error

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.cleanexamplekt.ui.errors.DefaultErrorFilter
import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import com.vanskarner.user.businesslogic.TypeInvalidation
import com.vanskarner.user.businesslogic.UserBusinessLogicError
import com.vanskarner.user.persistence.UserPersistenceError
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class DefaultErrorFilterTest {
    private lateinit var context: Context
    private lateinit var errorFilter: ErrorFilter

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        errorFilter = DefaultErrorFilter(context)
    }

    @Test
    fun filter_whenIsNotFound_shouldMatchMessage() {
        val expected = context.getString(R.string.msg_user_not_found)
        val actual = errorFilter.filter(UserPersistenceError.NotFound)

        assertEquals(expected, actual)
    }

    @Test
    fun filter_whenIsInvalidation_shouldMatchMessage() {
        val expected = buildString {
            appendLine(context.getString(R.string.msg_invalid_name))
            appendLine(context.getString(R.string.msg_invalid_age))
        }
        val types = listOf(TypeInvalidation.InvalidName, TypeInvalidation.InvalidAge)
        val actual = errorFilter.filter(UserBusinessLogicError.Invalidation(types))

        assertEquals(expected, actual)
    }

}