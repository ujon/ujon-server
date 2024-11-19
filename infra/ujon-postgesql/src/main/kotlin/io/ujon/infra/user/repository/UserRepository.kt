package io.ujon.infra.user.repository

import io.ujon.infra.user.entity.User
import io.ujon.infra.user.projection.UserSecret
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID>, UserQueryDslRepository {
}

interface UserQueryDslRepository {
    fun findByEmail(email: String): User?
    fun findByPasscode(passcode: String): User?
    fun findByUsername(username: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
}