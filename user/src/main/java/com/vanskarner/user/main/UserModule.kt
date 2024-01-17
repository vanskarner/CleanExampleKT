package com.vanskarner.user.main

import com.vanskarner.user.DefaultUserComponent
import com.vanskarner.user.UserComponent
import com.vanskarner.user.businesslogic.UserRepository
import com.vanskarner.user.persistence.DefaultUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindComponent(component: DefaultUserComponent): UserComponent

    @Binds
    @Singleton
    abstract fun bindRepository(repository: DefaultUserRepository): UserRepository

}