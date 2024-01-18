package com.vanskarner.user.businesslogic

import javax.inject.Inject

internal class ValidateUserUseCase @Inject constructor() {

    fun execute(userData: UserData): Result<UserData> {
        val exceptionList = validateUserData(userData)
        return if (exceptionList.isNotEmpty())
            Result.failure(UserBusinessLogicError.Invalidation(exceptionList))
        else Result.success(userData)
    }

    private fun validateUserData(userData: UserData): List<TypeInvalidation> {
        val exceptionList = mutableListOf<TypeInvalidation>()
        validateName(userData, exceptionList)
        validateAge(userData, exceptionList)
        return exceptionList.toList()
    }

    private fun validateName(userData: UserData, errorList: MutableList<TypeInvalidation>) {
        if (userData.name.isEmpty() || userData.name.isBlank())
            errorList.add(TypeInvalidation.InvalidName)
    }

    private fun validateAge(userData: UserData, errorList: MutableList<TypeInvalidation>) {
        if (userData.age < 18) errorList.add(TypeInvalidation.InvalidAge)
    }

}