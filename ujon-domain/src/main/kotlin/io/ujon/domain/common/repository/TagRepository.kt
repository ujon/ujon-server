package io.ujon.domain.common.repository

import io.ujon.domain.common.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByNameIn(names: Set<String>): Set<Tag>
    fun findByIdIn(ids: Set<Long>): Set<Tag>
}