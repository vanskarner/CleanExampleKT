package com.vanskarner.user

import com.vanskarner.user.businesslogic.FindUserUseCase
import com.vanskarner.user.businesslogic.GetUserListUseCase
import com.vanskarner.user.businesslogic.SaveUserUseCase
import com.vanskarner.user.businesslogic.UserData
import javax.inject.Inject
import javax.inject.Provider

internal class DefaultUserComponent @Inject constructor(
    private val saveUserUseCase: Provider<SaveUserUseCase>,
    private val getUserListUseCase: Provider<GetUserListUseCase>,
    private val findUserUseCase: Provider<FindUserUseCase>
) : UserComponent {

    override suspend fun save(userData: UserData) = saveUserUseCase.get().execute(userData)

    override suspend fun getList(): Result<List<UserData>> = getUserListUseCase.get().execute()

    override suspend fun find(userId: Int) = findUserUseCase.get().execute(userId)

}