package io.ujon.infra.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_email", schema = "ujon")
class UserEmail(
    @Column(name = "email", nullable = false, length = 50)
    val email: String,
    @Column(name = "is_primary", nullable = false)
    val primary: Boolean = false,
) {
    @Id
    @Column(name = "user_email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userEmailId: Int? = null

    @Column(name = "is_confirmed", nullable = false)
    val confirmed: Boolean = false

    // fk: target
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null
}