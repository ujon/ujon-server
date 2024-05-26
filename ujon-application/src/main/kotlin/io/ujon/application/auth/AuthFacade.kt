package io.ujon.application.auth

import io.ujon.application.auth.dto.input.SignInInput
import io.ujon.application.auth.dto.input.SignUpInput
import io.ujon.application.user.dto.output.UserSecretOutput
import io.ujon.application.auth.dto.output.AuthorityOutput
import io.ujon.application.auth.dto.output.ClaimsOutput

interface AuthFacade {
    fun signUp(input: SignUpInput.Email): AuthorityOutput
    fun signIn(input: SignInInput.Email): AuthorityOutput
    fun refresh(token: String): AuthorityOutput
    fun verifyToken(accessToken: String): ClaimsOutput
}