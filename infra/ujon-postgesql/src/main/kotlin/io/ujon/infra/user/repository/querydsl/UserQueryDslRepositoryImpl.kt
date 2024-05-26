package io.ujon.infra.user.repository.querydsl

import com.querydsl.core.types.Projections
import io.ujon.infra.role.entity.QRole.Companion.role
import io.ujon.infra.user.entity.QUser.Companion.user
import io.ujon.infra.user.entity.QUserEmail.Companion.userEmail
import io.ujon.infra.user.entity.User
import io.ujon.infra.user.projection.UserSecret
import io.ujon.infra.user.repository.UserQueryDslRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserQueryDslRepositoryImpl : QuerydslRepositorySupport(User::class.java), UserQueryDslRepository {
    override fun findByEmail(email: String): User? = from(user)
        .leftJoin(userEmail).on(userEmail.user.userId.eq(user.userId))
        .where(userEmail.email.eq(email))
        .fetchOne()

    override fun findUserSecretByEmail(email: String): UserSecret? = from(user)
        .select(userSecretProjection())
        .leftJoin(role).on(user.roleId.eq(role.roleId))
        .leftJoin(userEmail).on(userEmail.user.userId.eq(user.userId))
        .where(userEmail.email.eq(email))
        .fetchOne()

    override fun findUserSecretByUsername(username: String): UserSecret? = from(user)
        .select(userSecretProjection())
        .leftJoin(role).on(user.roleId.eq(role.roleId))
        .where(user.username.eq(username))
        .fetchOne()

    override fun existsByEmail(email: String): Boolean = from(userEmail)
        .where(userEmail.email.eq(email))
        .fetchOne() != null

    override fun existsByUsername(username: String): Boolean = from(user)
        .where(user.username.eq(username))
        .fetchOne() != null

    private fun userSecretProjection() = Projections.constructor(
        UserSecret::class.java,
        user,
        role
    )
}