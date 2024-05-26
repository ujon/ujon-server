package io.ujon.application.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.auth.jwt")
data class JwtProperties(
    val issuer: String,
    val accessTokenSecret: String,
    val accessTokenExpiresIn: Long,
    val refreshTokenSecret: String,
    val refreshTokenExpiresIn: Long,
)
