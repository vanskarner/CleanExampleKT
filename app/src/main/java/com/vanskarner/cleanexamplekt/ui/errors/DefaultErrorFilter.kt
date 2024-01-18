package com.vanskarner.cleanexamplekt.ui.errors

import android.content.Context
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.user.businesslogic.TypeInvalidation
import com.vanskarner.user.businesslogic.UserBusinessLogicError
import com.vanskarner.user.persistence.UserPersistenceError
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultErrorFilter @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorFilter {

    override fun filter(throwable: Throwable): String {
        return when (throwable) {
            is UserPersistenceError -> {
                when (throwable) {
                    UserPersistenceError.NotFound -> context.getString(R.string.msg_user_not_found)
                }
            }

            is UserBusinessLogicError -> {
                when (throwable) {
                    is UserBusinessLogicError.Invalidation -> buildString {
                        throwable.types.forEach {
                            appendLine(
                                when (it) {
                                    TypeInvalidation.InvalidAge -> context.getString(R.string.msg_invalid_age)
                                    TypeInvalidation.InvalidName -> context.getString(R.string.msg_invalid_name)
                                }
                            )
                        }
                    }
                }
            }

            else -> context.getString(R.string.msg_unknown_error)
        }
    }

}