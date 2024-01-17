package com.vanskarner.user.businesslogic

internal interface UserRepository {

    suspend fun save(userData: UserData): Result<Unit>

    suspend fun list(): Result<List<UserData>>

    suspend fun find(userId: Int): Result<UserData>

}