package io.ujon.api.common.middleware

import io.ujon.api.common.dto.CommonResponse
import io.ujon.common.exception.CommonException
import io.ujon.common.exception.ResponseType
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(e: CommonException): ResponseEntity<CommonResponse<*>> {
        val body = CommonResponse.exception(
            type = e.type,
            message = e.description,
            errors = e.errors,
        )
        printLog(e.type, e.description, e.stackTrace)
        return ResponseEntity
            .status(e.type.httpStatus)
            .body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<CommonResponse<Nothing>> {
        val type = ResponseType.UNKNOWN
        val body = CommonResponse.exception(
            type = type,
            message = e.message,
        )

        printLog(type, e.message, e.stackTrace)
        return ResponseEntity
            .status(type.httpStatus)
            .body(body)
    }

    private fun printLog(type: ResponseType, message: String?, stackTrace: Array<StackTraceElement>) {
//        val stackTraceList = stackTrace.toList().map { "\n${it.toFormat()}" }
//        val messageTemplate = "[Exception]\ncode: ${type.code},\nmessage: ${msg},\nstack trace: $stackTraceList"
        when (type.logLevel) {
            Level.ERROR -> log.error(message)
            Level.WARN -> log.warn(message)
            Level.INFO -> log.info(message)
            Level.DEBUG -> log.debug(message)
            Level.TRACE -> log.trace(message)
        }
    }

}