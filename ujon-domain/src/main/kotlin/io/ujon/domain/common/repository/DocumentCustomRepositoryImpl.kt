package io.ujon.domain.common.repository

import com.querydsl.core.types.dsl.Wildcard.count
import com.querydsl.jpa.JPAExpressions.select
import com.querydsl.jpa.impl.JPAQueryFactory
import io.ujon.domain.common.entity.Document
import io.ujon.domain.common.entity.QDocument.Companion.document
import io.ujon.domain.common.entity.QDocumentTag.Companion.documentTag
import io.ujon.domain.common.entity.QTag.Companion.tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class DocumentCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Document::class.java), DocumentCustomRepository {

}