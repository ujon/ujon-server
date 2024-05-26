package io.ujon.common.exception

import org.slf4j.event.Level
import org.springframework.http.HttpStatus

enum class ResponseType(
    val httpStatus: HttpStatus,
    val description: String,
    val logLevel: Level = Level.INFO
) {
    // COMMON
    SUCCESS(HttpStatus.OK, "Success"),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter"),

    // AUTH
    REQUIRED_TOKEN(HttpStatus.BAD_REQUEST, "Token required"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "The token has expired"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "The token uses an unsupported type or algorithm"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "invalid password"),

    // USER
    USER_NOT_EXIST(HttpStatus.NOT_FOUND, "User is not exist"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "Email already exist"),
    USERNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "Username already exist"),

    // ROLE
    ROLE_NOT_EXIST(HttpStatus.NOT_FOUND, "Role is not exist"),
}