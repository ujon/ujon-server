package io.ujon.core.exception

import io.ujon.core.type.ResponseType

abstract class BaseException(
    open val type: ResponseType = ResponseType.UNKNOWN,
    open val errors: List<Any>? = null,
    override val message: String? = ResponseType.UNKNOWN.message
) : RuntimeException()