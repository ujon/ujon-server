package io.ujon.api.auth.request

class SignInRequest {
    data class Email(
        val email: String,
        val password: String,
    )

    data class Passcode(
        val passcode: String,
    )
}