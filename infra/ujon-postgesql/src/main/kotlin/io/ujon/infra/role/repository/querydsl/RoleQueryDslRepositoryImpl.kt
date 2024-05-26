package io.ujon.infra.role.repository.querydsl

import com.querydsl.jpa.JPAExpressions.selectFrom
import io.ujon.infra.role.entity.QRole.Companion.role
import io.ujon.infra.role.entity.Role
import io.ujon.infra.role.repository.RoleQueryDslRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class RoleQueryDslRepositoryImpl : QuerydslRepositorySupport(Role::class.java), RoleQueryDslRepository {
    override fun default(): Role = from(role)
        .where(role.name.eq("user"))
        .fetchOne()

}