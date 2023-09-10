package io.ujon.api.blog.request

data class CreateDocumentRequest(
    val markdown: String,
    val tags: Set<String>
)
