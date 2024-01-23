package com.vanskarner.cleanexamplekt.ui.errors

import javax.inject.Inject

internal class DefaultErrorFilter @Inject constructor(
    private val defaultError: UnexpectedError,
    private val errorMap: Map<Class<*>, @JvmSuppressWildcards ErrorView<out Throwable>>
) : ErrorFilter {

    override fun filter(throwable: Throwable) = getErrorView(throwable).getMsg(throwable)

    @Suppress("UNCHECKED_CAST")
    private fun getErrorView(throwable: Throwable) =
        errorMap.getOrDefault(throwable.javaClass, defaultError) as ErrorView<Throwable>

}