package com.vanskarner.cleanexamplekt.ui.errors

interface ErrorView<T : Throwable> {

    fun getMsg(error: T): String

}