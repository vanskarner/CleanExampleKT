package com.vanskarner.user.businesslogic

import javax.inject.Inject

internal class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val validateUserUseCase: ValidateUserUseCase
) {

    suspend fun execute(userData: UserData) =
        validateUserUseCase.execute(userData)
            .mapCatching { repository.save(it).getOrThrow() }

}