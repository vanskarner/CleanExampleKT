package com.vanskarner.user.businesslogic

import com.vanskarner.user.persistence.UserDTO
import com.vanskarner.user.persistence.toDTO
import com.vanskarner.user.persistence.toData

class FakeUserRepository : UserRepository {
    private val mutableList = mutableListOf<UserDTO>()

    override suspend fun save(userData: UserData): Result<Unit> {
        val resultUser = userData.copy(userId = mutableList.size + 1)
        mutableList.add(resultUser.toDTO())
        return Result.success(Unit)
    }

    override suspend fun list(): Result<List<UserData>> {
        val list = mutableList.map { it.toData() }
        return Result.success(list)
    }

    override suspend fun find(userId: Int): Result<UserData> {
        return try {
            val userFound = mutableList.first { it.id == userId }
            Result.success(userFound.toData())
        } catch (ex: NoSuchElementException) {
            Result.failure(NoSuchElementException())
        }
    }

}