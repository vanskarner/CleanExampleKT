package com.vanskarner.cleanexamplekt.ui.user

import com.vanskarner.user.businesslogic.UserData

fun UserData.toModel() = UserModel(userId, name, age)