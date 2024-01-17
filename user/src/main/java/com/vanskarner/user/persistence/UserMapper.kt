package com.vanskarner.user.persistence

import com.vanskarner.user.businesslogic.UserData

internal fun UserData.toDTO() = UserDTO(userId, name, age)

internal fun UserDTO.toData() = UserData(id, name, age)