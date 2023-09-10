package io.ujon.domain.document

import io.ujon.core.primitive.Page
import io.ujon.core.primitive.Pageable
import io.ujon.domain.common.entity.Tag
import io.ujon.domain.common.repository.DocumentRepository
import io.ujon.domain.common.repository.TagRepository
import io.ujon.domain.common.utils.request
import io.ujon.domain.common.utils.spec
import io.ujon.domain.document.dto.command.CreateDocumentCommand
import io.ujon.domain.document.dto.spec.DocumentSpec
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
    private val tagRepository: TagRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    // create
    fun createDocument(command: CreateDocumentCommand) {
        val tags = getTags(command.tags)
        val document = command.toEntity()
        document.updateTags(tags)
        documentRepository.save(document)
    }

    // read
    @Transactional(readOnly = true)
    fun get(pageable: Pageable): Page<DocumentSpec> {
        val documents = documentRepository.findAll(pageable.request())
        return documents.spec { it.toSpec() }
    }

    // functional
    private fun getTags(tagNames: Set<String>): Set<Tag> {
        val tags = tagRepository.findByNameIn(tagNames)
        val existTagNames = tags.map { it.name }
        val newTags = tagNames
            .filterNot { existTagNames.contains(it) }
            .map { Tag(name = it) }
            .let { tagRepository.saveAll(it) }

        if (newTags.isNotEmpty()) {
            val newTagIds = newTags.joinToString(", ") { it.id.toString() }
            log.info("new tag created. ids: $newTagIds")
        }
        return (tags + newTags).toSet()
    }
}