package io.ujon.domain.user

import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.domain.user.dto.info.UserSecretInfo
import io.ujon.domain.user.dto.operation.RegisterUserOperation
import io.ujon.domain.user.dto.operation.RetrieveUserOperation
import io.ujon.domain.user.dto.operation.RetrieveUserSecretOperation

interface UserService {
    fun registerUser(operation: RegisterUserOperation.Email): UserInfo
    fun retrieveUser(operation: RetrieveUserOperation): UserInfo
    fun retrieveUserSecret(operation: RetrieveUserSecretOperation.Email): UserSecretInfo
    fun retrieveUserSecret(operation: RetrieveUserSecretOperation.Username): UserSecretInfo
}