package io.ujon.domain.document.dto.spec

import io.ujon.domain.common.entity.DocumentType
import java.util.*

data class DocumentSpec(
    val id: UUID,
    val type: DocumentType,
    val markdown: String?,
    val tags: Set<String>
)
