package io.ujon.api.auth

import io.ujon.api.auth.mapper.AuthControllerMapper
import io.ujon.api.auth.request.RefreshRequest
import io.ujon.api.auth.request.SignInRequest
import io.ujon.api.auth.request.SignUpRequest
import io.ujon.application.auth.AuthFacade
import io.ujon.application.auth.dto.output.AuthorityOutput
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authFacade: AuthFacade,
    private val authMapper: AuthControllerMapper,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest.Email): AuthorityOutput {
        val input = authMapper.toSignUpInputEmail(request)
        return authFacade.signUp(input)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest.Email): AuthorityOutput {
        val input = authMapper.toSignInInputEmail(request)
        return authFacade.signIn(input)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): AuthorityOutput {
        return authFacade.refresh(request.refreshToken)
    }

    @GetMapping("/verify-token")
    fun verifyToken(): Boolean {
        authFacade.verifyToken("todo")
        return true
    }
}