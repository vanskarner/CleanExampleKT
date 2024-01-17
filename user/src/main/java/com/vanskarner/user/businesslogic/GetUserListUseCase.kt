package com.vanskarner.user.businesslogic

import javax.inject.Inject

internal class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute() = userRepository.list()

}