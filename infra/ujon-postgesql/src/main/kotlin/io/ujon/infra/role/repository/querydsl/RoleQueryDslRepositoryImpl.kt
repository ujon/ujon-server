package io.ujon.infra.role.repository.querydsl

import io.ujon.infra.role.entity.QRole.Companion.role
import io.ujon.infra.role.entity.Role
import io.ujon.infra.role.repository.RoleQueryDslRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class RoleQueryDslRepositoryImpl : QuerydslRepositorySupport(Role::class.java), RoleQueryDslRepository {
    override fun default(): Role = from(role)
        .where(role.name.eq("user"))
        .fetchOne()

}