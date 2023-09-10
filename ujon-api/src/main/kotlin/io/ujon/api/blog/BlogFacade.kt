package io.ujon.api.blog

import io.ujon.api.blog.request.CreateDocumentRequest
import io.ujon.api.blog.response.DocumentResponse
import io.ujon.api.common.utils.model
import io.ujon.core.primitive.Page
import io.ujon.core.primitive.Pageable
import io.ujon.domain.document.DocumentService
import io.ujon.domain.document.dto.command.CreateDocumentCommand
import org.hashids.Hashids
import org.springframework.stereotype.Component

@Component
class BlogFacade(
    private val hashids: Hashids,
    private val documentService: DocumentService
) {

    fun createDocument(request: CreateDocumentRequest) {
        val command = CreateDocumentCommand(
            markdown = request.markdown,
            tags = request.tags
        )
        documentService.createDocument(command)
    }

    fun getDocuments(pageable: Pageable): Page<DocumentResponse> {
        val document = documentService.get(pageable)
        return document.model { it.toResponse(hashids) }
    }
}