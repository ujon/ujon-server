package io.ujon.application.auth.dto.input

class SignUpInput {
    data class Email(
        val email: String,
        val name: String?,
        val password: String,
        val username: String,
    )
}
