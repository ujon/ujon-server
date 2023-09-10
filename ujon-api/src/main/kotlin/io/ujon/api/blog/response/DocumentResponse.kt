package io.ujon.api.blog.response

import io.ujon.domain.common.entity.DocumentType

data class DocumentResponse(
    val id: String,
    val type: DocumentType,
    val markdown: String?,
    val tags: Set<String>
)