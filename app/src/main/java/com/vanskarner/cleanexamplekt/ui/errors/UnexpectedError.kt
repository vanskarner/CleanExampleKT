package com.vanskarner.cleanexamplekt.ui.errors

import android.content.Context
import com.vanskarner.cleanexamplekt.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UnexpectedError @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorView<Throwable> {

    override fun getMsg(error: Throwable) =
        context.getString(R.string.msg_unknown_error)

}