package io.ujon.application.auth.mapper

import io.ujon.application.auth.dto.input.SignInInput
import io.ujon.application.auth.dto.input.SignUpInput
import io.ujon.domain.user.dto.operation.RegisterUserOperation
import io.ujon.domain.user.dto.operation.RetrieveUserSecretOperation
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface AuthFacadeMapper {
    // input -> operation
    @Mapping(target = "password", source = "encodedPassword")
    fun toRegisterUserOperationEmail(input: SignUpInput.Email, encodedPassword: String): RegisterUserOperation.Email

    @Mapping(target = "copy", ignore = true)
    fun toRetrieveUserSecretOperation(input: SignInInput.Email): RetrieveUserSecretOperation.Email
}