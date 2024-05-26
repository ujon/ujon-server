package io.ujon.application.user

import io.ujon.application.user.dto.input.RetrieveUserSecret
import io.ujon.application.user.dto.output.UserSecretOutput
import io.ujon.domain.user.UserService
import io.ujon.domain.user.dto.operation.RetrieveUserSecretOperation
import org.springframework.stereotype.Component

@Component
class UserFacadeImpl(
    // service
    private val userService: UserService
) : UserFacade {
    override fun retrieveUserSecret(input: RetrieveUserSecret): UserSecretOutput {
        val operation = RetrieveUserSecretOperation.Username(
            username = input.username
        )
        val info = userService.retrieveUserSecret(operation)
        return UserSecretOutput(
            userId = info.userId,
            role = info.name
        )
    }
}