package io.ujon.application.user

import io.ujon.application.user.dto.input.RetrieveUserSecret
import io.ujon.application.user.dto.output.UserSecretOutput

interface UserFacade {
    fun retrieveUserSecret(input: RetrieveUserSecret): UserSecretOutput
}