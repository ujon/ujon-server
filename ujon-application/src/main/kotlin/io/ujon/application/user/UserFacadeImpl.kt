package io.ujon.application.user

import io.ujon.application.user.dto.input.RetrieveUserSecret
import io.ujon.application.user.dto.output.UserSecretOutput
import io.ujon.domain.user.UserService
import org.springframework.stereotype.Component

@Component
class UserFacadeImpl(
    // service
    private val userService: UserService
) : UserFacade {
    override fun retrieveUserSecret(input: RetrieveUserSecret): UserSecretOutput {
        val info = userService.retrieve(input.username)
        return UserSecretOutput(
            userId = info.userId,
            role = info.role
        )
    }
}