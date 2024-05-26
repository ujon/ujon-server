package io.ujon.application.auth.dto.input

class SignInInput {
    data class Email(
        val email: String,
        val password: String,
    )
}
