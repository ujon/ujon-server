package io.ujon.infra.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(
        name = "created_at",
        updatable = false,
        nullable = false,
        columnDefinition = "timestamp with time zone not null default now()"
    )
    var createdAt: Instant = Instant.now()

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp with time zone not null default now()")
    var updatedAt: Instant = Instant.now()

    @LastModifiedBy
    @Column(updatable = false)
    var updatedBy: String? = null
}