package io.ujon.domain.document.dto.command

data class CreateDocumentCommand(
    var markdown: String,
    var tags: Set<String> = emptySet(),
)

