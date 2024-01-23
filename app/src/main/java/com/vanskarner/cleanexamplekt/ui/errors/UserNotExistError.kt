package com.vanskarner.cleanexamplekt.ui.errors

import android.content.Context
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.user.persistence.UserPersistenceError
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class UserNotExistError @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorView<UserPersistenceError.NotFound> {

    override fun getMsg(error: UserPersistenceError.NotFound) =
        context.getString(R.string.msg_user_not_found)

}