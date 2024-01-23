package com.vanskarner.cleanexamplekt.ui.errors

import com.vanskarner.user.businesslogic.UserBusinessLogicError
import com.vanskarner.user.persistence.UserPersistenceError
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class TypesErrorModule {

    @Binds
    @IntoMap
    @ClassKey(UserPersistenceError.NotFound::class)
    abstract fun bindNotFoundError(error: UserNotExistError): ErrorView<*>

    @Binds
    @IntoMap
    @ClassKey(UserBusinessLogicError.Invalidation::class)
    abstract fun bindValidationError(error: UserValidationError): ErrorView<*>

    @Binds
    @Singleton
    abstract fun bindErrorFilter(filter: DefaultErrorFilter): ErrorFilter

}