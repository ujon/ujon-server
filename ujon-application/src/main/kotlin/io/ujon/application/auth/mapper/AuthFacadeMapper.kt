package io.ujon.application.auth.mapper

import io.ujon.application.auth.dto.input.SignUpInput
import io.ujon.domain.user.dto.operation.RegisterUserOperation
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthFacadeMapper {
    // input -> operation
    fun toRegisterUserOperationEmail(input: SignUpInput.Email): RegisterUserOperation.Email
}