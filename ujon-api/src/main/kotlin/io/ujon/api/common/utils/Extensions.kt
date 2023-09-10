package io.ujon.api.common.utils

import io.ujon.core.primitive.Page

fun <T, R> Page<T>.model(mapper: (T) -> R): Page<R> {
    return Page(
        content = this.content.map(mapper),
        isLast = this.isLast,
        isFirst = this.isFirst,
        isEmpty = this.isEmpty
    )
}