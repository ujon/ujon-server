package io.ujon.infra.user.repository

import io.ujon.infra.user.entity.User
import io.ujon.infra.user.projection.UserSecret
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, UUID>, UserQueryDslRepository {
    fun findByUsername(username: String): User?
}

interface UserQueryDslRepository {
    fun findByEmail(email: String): User?
    fun findUserSecretByEmail(email: String): UserSecret?
    fun findUserSecretByUsername(username: String): UserSecret?
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
}