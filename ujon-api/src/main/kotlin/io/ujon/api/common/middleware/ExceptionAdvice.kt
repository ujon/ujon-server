package io.ujon.api.common.middleware

import io.ujon.api.common.response.ServiceResponse
import io.ujon.core.exception.BaseException
import io.ujon.core.type.ResponseType
import io.ujon.core.utils.JsonUtil.jsonString
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ServiceResponse<Any>> {
        handleBaseExceptionLog(e)
        return handleResponse(
            type = e.type,
            message = e.message,
            errors = e.errors
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ServiceResponse<Nothing>> {
        val type = ResponseType.UNKNOWN
        log.error(
            "${type.message}.\n${
                jsonString(
                    Pair("code", type.code),
                    Pair("errors", e.message),
                    Pair("stacktrace", e.stackTrace)
                )
            }"
        )
        return handleResponse(
            type = type,
            message = e.message
        )
    }

    private fun handleBaseExceptionLog(e: BaseException) {
        val logMessage = e.message ?: e.type.message
        val logDetail = e.errors?.let { "\n${jsonString(Pair("code", e.type.code), Pair("errors", it))}" } ?: ""
        val logResult = "$logMessage$logDetail"

        when (e.type.logLevel) {
            Level.DEBUG -> log.debug(logMessage)
            Level.INFO -> log.info(logResult)
            Level.WARN -> log.warn(logResult)
            Level.ERROR -> log.error(logResult)
            Level.TRACE -> log.trace(logResult)
        }
    }

    private fun <T> handleResponse(
        type: ResponseType,
        message: String? = null,
        errors: List<T>? = null
    ): ResponseEntity<ServiceResponse<T>> {
        val body = ServiceResponse.error(
            type = type,
            message = message,
            errors = errors
        )
        return ResponseEntity
            .status(type.status)
            .body(body)
    }
}