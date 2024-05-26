package io.ujon.infra.role.entity

import io.ujon.common.key.UUIDHelper
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "role", schema = "ujon")
class Role(
    @Column(name = "name", unique = true)
    val name: String
) {
    @Id
    @Column(name = "role_id")
    val roleId: UUID = UUIDHelper.v7()
}