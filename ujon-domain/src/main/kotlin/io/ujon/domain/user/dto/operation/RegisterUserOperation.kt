package io.ujon.domain.user.dto.operation

class RegisterUserOperation {
    data class Email(
        val email: String,
        val name: String?,
        val password: String,
        val username: String,
    )

    data class Social(
        val email: String,
        val name: String?,
        val password: String,
        val username: String,
    )
}