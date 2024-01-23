package com.vanskarner.cleanexamplekt.ui.main

import com.vanskarner.cleanexamplekt.ui.errors.DefaultErrorFilter
import com.vanskarner.cleanexamplekt.ui.errors.ErrorFilter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class UIModule {

    @Binds
    @Singleton
    abstract fun bindErrorFilter(filter: DefaultErrorFilter): ErrorFilter

}