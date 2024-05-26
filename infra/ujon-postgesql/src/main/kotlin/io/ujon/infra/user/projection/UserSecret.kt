package io.ujon.infra.user.projection

import com.querydsl.core.annotations.QueryProjection
import io.ujon.infra.role.entity.Role
import io.ujon.infra.user.entity.User
import java.util.UUID

data class UserSecret(
    val userId: UUID,
    val username: String,
    val password: String?,
    val name: String?,
    val email: String,
    val role: String,
) {
    @QueryProjection
    constructor(
        user: User,
        role: Role,
    ) : this(
        userId = user.userId,
        username = user.username,
        password = user.password,
        name = user.name,
        email = user.primaryEmail().email,
        role = role.name
    )
}