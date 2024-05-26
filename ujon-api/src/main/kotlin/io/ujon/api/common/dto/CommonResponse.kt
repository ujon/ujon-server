package io.ujon.api.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.ujon.common.exception.ResponseType

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonResponse<T>(
    val success: Boolean,
    val message: String?,
    val payload: T? = null,
    val errors: T? = null,
) {
    companion object {
        fun <T> success(payload: T): CommonResponse<T> {
            return CommonResponse(
                success = true,
                message = ResponseType.SUCCESS.description,
                payload = payload,
            )
        }

        fun exception(type: ResponseType, message: String?): CommonResponse<Nothing> {
            return CommonResponse(
                success = false,
                message = message ?: type.description,
            )
        }

        fun exception(type: ResponseType, message: String?, errors: List<String>?): CommonResponse<List<Any>?> {
            return CommonResponse(
                success = false,
                message = message ?: type.description,
                errors = errors
            )
        }
    }
}
