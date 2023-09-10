package io.ujon.api.common.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.ujon.core.type.ResponseType
import java.time.Instant

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ServiceResponse<T>(
    val code: Int,
    val message: String?,
    val payload: T? = null,
    val errors: List<T>? = null,
    val timestamp: Instant = Instant.now()
) {
    companion object {
        fun <T> success(payload: T?): ServiceResponse<T> {
            return ServiceResponse(
                code = ResponseType.SUCCESS.code,
                message = ResponseType.SUCCESS.message,
                payload = payload
            )
        }

        fun <T> error(type: ResponseType, message: String? = null, errors: List<T>? = emptyList()): ServiceResponse<T> {
            return ServiceResponse(
                code = type.code,
                message = message ?: type.message,
                errors = errors
            )
        }
    }
}