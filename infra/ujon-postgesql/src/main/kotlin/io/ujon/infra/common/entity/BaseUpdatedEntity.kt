package io.ujon.infra.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseUpdatedEntity {

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp with time zone not null default now()")
    var updatedAt: Instant = Instant.now()

    @LastModifiedBy
    @Column(updatable = false)
    var updatedBy: String? = null
}