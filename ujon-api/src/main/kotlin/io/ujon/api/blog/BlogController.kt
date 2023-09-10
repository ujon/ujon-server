package io.ujon.api.blog

import io.ujon.api.blog.request.CreateDocumentRequest
import io.ujon.api.blog.response.DocumentResponse
import io.ujon.core.primitive.Page
import io.ujon.core.primitive.Pageable
import io.ujon.domain.document.DocumentService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BlogController(
    private val blogFacade: BlogFacade,
    private val documentService: DocumentService

) {

    @PostMapping("/blog/document")
    fun createDocument(@Valid @RequestBody request: CreateDocumentRequest) {
        blogFacade.createDocument(request)
    }

    @GetMapping("/blog/documents")
    fun getDocuments(pageable: Pageable): Page<DocumentResponse> {
        return blogFacade.getDocuments(pageable)
    }
}