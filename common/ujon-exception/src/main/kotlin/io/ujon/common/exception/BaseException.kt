package io.ujon.common.exception

abstract class BaseException(
    type: ResponseType,
    description: String?,
    errors: List<String>?,
) : RuntimeException(description ?: type.description)