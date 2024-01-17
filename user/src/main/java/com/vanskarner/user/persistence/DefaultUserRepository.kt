package com.vanskarner.user.persistence

import com.vanskarner.user.businesslogic.UserData
import com.vanskarner.user.businesslogic.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class DefaultUserRepository @Inject constructor(): UserRepository {
    private val mutableList = mutableListOf<UserDTO>()

    override suspend fun save(userData: UserData): Result<Unit> {
        delay(500)
        val resultUser = userData.copy(userId = mutableList.size + 1)
        mutableList.add(resultUser.toDTO())
        return Result.success(Unit)
    }

    override suspend fun list(): Result<List<UserData>> {
        delay(500)
        val list = mutableList.map { it.toData() }
        return Result.success(list)
    }

    override suspend fun find(userId: Int): Result<UserData> {
        delay(500)
        return try {
            val userFound = mutableList.first { it.id == userId }
            Result.success(userFound.toData())
        } catch (ex: NoSuchElementException) {
            Result.failure(UserPersistenceError.NotFound)
        }
    }

}