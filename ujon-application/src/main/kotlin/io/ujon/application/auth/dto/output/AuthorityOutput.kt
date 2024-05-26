package io.ujon.application.auth.dto.output

data class AuthorityOutput(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
)