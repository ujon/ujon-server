package io.ujon.domain.common.entity

import com.fasterxml.jackson.annotation.JsonValue

enum class DocumentType(
    private val value: String
) {
    MARKDOWN("markdown");

    @JsonValue
    fun value(): String = this.value

    companion object {
        fun fromValue(value: String): DocumentType? = values().find { it.value == value }
    }
}