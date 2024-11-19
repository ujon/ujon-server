package io.ujon.api.auth.request

class SignUpRequest {
    data class Email(
        val email: String,
        val name: String?,
        val password: String,
        val username: String,
    )
}