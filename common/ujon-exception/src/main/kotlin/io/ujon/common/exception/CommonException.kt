package io.ujon.common.exception

data class CommonException(
    val type: ResponseType,
    val description: String? = null,
    val errors: List<String>? = null,
) : BaseException(type, description, errors)
