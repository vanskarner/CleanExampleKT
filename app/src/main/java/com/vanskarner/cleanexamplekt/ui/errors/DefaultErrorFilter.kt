package com.vanskarner.cleanexamplekt.ui.errors

import javax.inject.Inject
import javax.inject.Provider

internal class DefaultErrorFilter @Inject constructor(
    private val defaultError: Provider<UnexpectedError>,
    private val errorMap: Map<Class<*>, @JvmSuppressWildcards Provider<ErrorView<*>>>
) : ErrorFilter {

    override fun filter(throwable: Throwable) = getErrorView(throwable).getMsg(throwable)

    @Suppress("UNCHECKED_CAST")
    private fun getErrorView(throwable: Throwable) =
        errorMap.getOrDefault(throwable.javaClass, defaultError)
            .get() as ErrorView<Throwable>


}