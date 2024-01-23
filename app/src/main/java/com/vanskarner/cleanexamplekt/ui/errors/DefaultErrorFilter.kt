package com.vanskarner.cleanexamplekt.ui.errors

import javax.inject.Inject

internal class DefaultErrorFilter @Inject constructor(
    private val defaultError: UnexpectedError,
    private val animalMap: Map<Class<*>, @JvmSuppressWildcards ErrorView<out Throwable>>
) : ErrorFilter {

    @Suppress("UNCHECKED_CAST")
    override fun filter(throwable: Throwable): String {
        val result =
            animalMap.getOrDefault(throwable.javaClass, defaultError) as ErrorView<Throwable>
        return result.getMsg(throwable)
    }

}