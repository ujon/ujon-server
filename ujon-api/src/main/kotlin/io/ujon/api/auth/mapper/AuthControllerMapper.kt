package io.ujon.api.auth.mapper

import io.ujon.api.auth.request.SignInRequest
import io.ujon.api.auth.request.SignUpRequest
import io.ujon.application.auth.dto.input.SignInInput
import io.ujon.application.auth.dto.input.SignUpInput
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthControllerMapper {
    // request -> input
    fun toSignUpInputEmail(request: SignUpRequest.Email): SignUpInput.Email
    fun toSignInInputEmail(request: SignInRequest.Email): SignInInput.Email
}