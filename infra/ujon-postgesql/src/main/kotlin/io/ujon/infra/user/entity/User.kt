package io.ujon.infra.user.entity

import io.ujon.common.key.UUIDHelper
import io.ujon.infra.common.entity.BaseCreatedEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "user", schema = "ujon")
class User(
    @Column(name = "username", unique = true, length = 50)
    val username: String,
    @Column(name = "password", length = 100)
    val password: String?,
    @Column(name = "name", length = 20)
    val name: String?,
    // fk: target
    @Column(name = "role_id")
    val roleId: UUID
) : BaseCreatedEntity() {
    @Id
    @Column(name = "user_id")
    val userId: UUID = UUIDHelper.v7()

    // fk: source
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val emails: MutableList<UserEmail> = mutableListOf()


    // logic
    fun addEmail(email: UserEmail) {
        emails.add(email)
        email.user = this
    }

    fun primaryEmail(): UserEmail = emails.first { it.primary }
}