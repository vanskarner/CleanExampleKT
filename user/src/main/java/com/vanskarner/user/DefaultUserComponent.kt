package com.vanskarner.user

import com.vanskarner.user.businesslogic.FindUserUseCase
import com.vanskarner.user.businesslogic.GetUserListUseCase
import com.vanskarner.user.businesslogic.SaveUserUseCase
import com.vanskarner.user.businesslogic.UserData
import javax.inject.Inject

internal class DefaultUserComponent @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserListUseCase: GetUserListUseCase,
    private val findUserUseCase: FindUserUseCase
) : UserComponent {

    override suspend fun save(userData: UserData) = saveUserUseCase.execute(userData)

    override suspend fun getList(): Result<List<UserData>> = getUserListUseCase.execute()

    override suspend fun find(userId: Int) = findUserUseCase.execute(userId)

}