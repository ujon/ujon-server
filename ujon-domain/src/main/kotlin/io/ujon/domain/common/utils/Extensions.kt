package io.ujon.domain.common.utils

import io.ujon.core.primitive.Page
import io.ujon.core.primitive.Pageable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Page as SpringDataPage

// Pageable
fun Pageable.request() = PageRequest.of(this.page, this.size)
fun Pageable.request(sort: Sort) = PageRequest.of(this.page, this.size, sort)

// Page
fun <T> SpringDataPage<T>.spec(): Page<T> {
    return Page(
        content = this.content,
        size = this.size,
        numberOfElements = this.numberOfElements,
        totalPages = this.totalPages,
        totalElements = this.totalElements,
        number = this.number,
        isLast = this.isLast,
        isFirst = this.isFirst,
        isEmpty = this.isEmpty
    )
}

fun <T, R> SpringDataPage<T>.spec(mapper: (T) -> R): Page<R> {
    return Page(
        content = this.content.map(mapper).toMutableList(),
        size = this.size,
        numberOfElements = this.numberOfElements,
        totalPages = this.totalPages,
        totalElements = this.totalElements,
        number = this.number,
        isLast = this.isLast,
        isFirst = this.isFirst,
        isEmpty = this.isEmpty
    )
}
