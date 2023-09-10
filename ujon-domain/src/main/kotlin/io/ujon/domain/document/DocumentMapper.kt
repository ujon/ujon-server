package io.ujon.domain.document

import io.ujon.domain.common.entity.Document
import io.ujon.domain.common.entity.DocumentType
import io.ujon.domain.document.dto.command.CreateDocumentCommand
import io.ujon.domain.document.dto.spec.DocumentSpec

// to spec
fun Document.toSpec(): DocumentSpec {
    val tags = this.documentTags.map { it.tag.name }.toSet()
    return DocumentSpec(
        id = this.id,
        type = this.type,
        markdown = this.textData,
        tags = tags
    )
}

// to entity
fun CreateDocumentCommand.toEntity() = Document(
    type = DocumentType.MARKDOWN,
    textData = this.markdown
)