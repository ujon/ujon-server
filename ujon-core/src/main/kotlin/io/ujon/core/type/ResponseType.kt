package io.ujon.core.type

import org.slf4j.event.Level
import org.springframework.http.HttpStatus

enum class ResponseType(
    val status: HttpStatus,
    val code: Int,
    val message: String,
    val logLevel: Level = Level.INFO
) {
    // ordinary
    SUCCESS(HttpStatus.OK, 200, "success"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "bad request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "unauthorized"),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "not found"),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, 500, "unknown error", Level.ERROR),
    // custom
    CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "client error", Level.ERROR),
}