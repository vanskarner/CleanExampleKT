package com.vanskarner.cleanexamplekt.ui.errors

interface ErrorView<T> where T : Throwable {

    fun getMsg(error: T): String

}