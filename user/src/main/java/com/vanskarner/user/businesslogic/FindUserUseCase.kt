package com.vanskarner.user.businesslogic

import javax.inject.Inject

internal class FindUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(userId: Int) = userRepository.find(userId)

}