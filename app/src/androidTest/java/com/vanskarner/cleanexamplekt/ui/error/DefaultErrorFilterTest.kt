package com.vanskarner.cleanexamplekt.ui.error

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.cleanexamplekt.ui.errors.DefaultErrorFilter
import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import com.vanskarner.cleanexamplekt.ui.errors.ErrorView
import com.vanskarner.cleanexamplekt.ui.errors.UnexpectedError
import com.vanskarner.cleanexamplekt.ui.errors.UserNotExistError
import com.vanskarner.cleanexamplekt.ui.errors.UserValidationError
import com.vanskarner.user.businesslogic.TypeInvalidation
import com.vanskarner.user.businesslogic.UserBusinessLogicError
import com.vanskarner.user.persistence.UserPersistenceError
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultErrorFilterTest {
    private lateinit var context: Context
    private lateinit var errorFilter: ErrorFilter

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val defaultError = UnexpectedError(context)
        val errorMap = HashMap<Class<*>, ErrorView<*>>()
        errorMap[UserBusinessLogicError.Invalidation::class.java] = UserValidationError(context)
        errorMap[UserPersistenceError.NotFound.javaClass] = UserNotExistError(context)

        errorFilter = DefaultErrorFilter(defaultError, errorMap)
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

    @Test
    fun filter_whenIsUnknown_shouldMatchMessage() {
        val expected = context.getString(R.string.msg_unknown_error)
        val unmappedException = RuntimeException("Not Mapped")
        val actual = errorFilter.filter(unmappedException)

        assertEquals(expected, actual)
    }

}