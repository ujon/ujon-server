package io.ujon.infra.role.repository

import io.ujon.infra.role.entity.Role
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, UUID>, RoleQueryDslRepository {
    fun findByRoleId(id: UUID): Role?
}


interface RoleQueryDslRepository {
    /**
     * Returns the default role.
     * @return the default role which is 'user'
     */
    fun default(): Role
}