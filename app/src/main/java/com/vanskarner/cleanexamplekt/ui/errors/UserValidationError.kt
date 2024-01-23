package com.vanskarner.cleanexamplekt.ui.errors

import android.content.Context
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.user.businesslogic.TypeInvalidation
import com.vanskarner.user.businesslogic.UserBusinessLogicError
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserValidationError @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorView<UserBusinessLogicError.Invalidation> {

    override fun getMsg(error: UserBusinessLogicError.Invalidation) = buildString {
        error.types.forEach { appendLine(filterInvalidation(it)) }
    }

    private fun filterInvalidation(it: TypeInvalidation) = when (it) {
        TypeInvalidation.InvalidAge -> context.getString(R.string.msg_invalid_age)
        TypeInvalidation.InvalidName -> context.getString(R.string.msg_invalid_name)
    }

}