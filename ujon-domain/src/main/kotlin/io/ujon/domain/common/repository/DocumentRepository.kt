package io.ujon.domain.common.repository

import io.ujon.domain.common.entity.Document
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DocumentRepository : JpaRepository<Document, UUID>, DocumentCustomRepository {
}

interface DocumentCustomRepository {
}