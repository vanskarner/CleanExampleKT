package com.vanskarner.cleanexamplekt.ui.errors

interface ErrorFilter {

    fun filter(throwable: Throwable): String

}