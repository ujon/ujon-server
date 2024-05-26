package io.ujon.application.auth.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import io.ujon.application.auth.config.JwtProperties
import io.ujon.application.auth.dto.output.AuthorityOutput
import io.ujon.application.auth.dto.output.ClaimsOutput
import io.ujon.application.auth.type.AuthTokenType
import io.ujon.common.exception.CommonException
import io.ujon.common.exception.ResponseType
import java.util.Date
import javax.crypto.SecretKey
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Component
class JwtHelper(
    private val jwtProperties: JwtProperties
) {
    private val log = LoggerFactory.getLogger(javaClass)

    private val tokenType = "bearer"

    fun authority(username: String): AuthorityOutput {
        val currentTime = Date()
        val accessToken = issueToken(
            subject = username,
            currentTime = currentTime,
            expiresIn = jwtProperties.accessTokenExpiresIn,
            key = jwtProperties.accessTokenSecret
        )
        val refreshToken = issueToken(
            subject = username,
            currentTime = currentTime,
            expiresIn = jwtProperties.refreshTokenExpiresIn,
            key = jwtProperties.refreshTokenSecret
        )
        return AuthorityOutput(
            tokenType = tokenType,
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = jwtProperties.accessTokenExpiresIn
        )
    }

    fun validate(token: String, type: AuthTokenType): ClaimsOutput {
        return try {
            val key = when (type) {
                AuthTokenType.ACCESS_TOKEN -> jwtProperties.accessTokenSecret
                AuthTokenType.REFRESH_TOKEN -> jwtProperties.refreshTokenSecret
            }
            val jws = Jwts.parser()
                .verifyWith(secretKey(key))
                .build()
                .parseSignedClaims(token)
                .takeIf { it.payload.expiration.after(Date()) } ?: throw CommonException(ResponseType.EXPIRED_TOKEN)
            ClaimsOutput(
                username = jws.payload.subject
            )
        } catch (e: Exception) {
            log.info("JWT validation failed: {}", e.message)
            when (e) {
                is SignatureException,
                is MalformedJwtException,
                is IllegalArgumentException -> throw CommonException(ResponseType.INVALID_TOKEN)

                is ExpiredJwtException -> throw CommonException(ResponseType.EXPIRED_TOKEN)
                is UnsupportedJwtException -> throw CommonException(ResponseType.UNSUPPORTED_TOKEN)
                else -> throw e
            }
        }
    }

    private fun issueToken(subject: String, currentTime: Date, expiresIn: Long, key: String): String {
        val claims = Jwts.claims()
            .issuer(jwtProperties.issuer)
            .subject(subject)
            .expiration(Date(currentTime.time + expiresIn))
            .notBefore(currentTime)
            .issuedAt(currentTime)
            .build()

        return Jwts.builder()
            .header()
            .type("JWT")
            .and()
            .claims(claims)
            .signWith(secretKey(key), Jwts.SIG.HS512)
            .compact()
    }

    private fun secretKey(key: String): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtProperties.accessTokenSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }

}