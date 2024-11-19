package io.ujon.domain.user

import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.domain.user.dto.operation.RegisterUserOperation

interface UserService {
    fun register(operation: RegisterUserOperation.Email): UserInfo
    fun retrieve(username: String): UserInfo
    fun verify(passcode: String): UserInfo
    fun verify(email: String, password: String): UserInfo
}