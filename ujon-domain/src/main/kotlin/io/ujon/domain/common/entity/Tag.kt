package io.ujon.domain.common.entity

import jakarta.persistence.*

@Entity
@Table(name = "tag", schema = "ujon")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name", nullable = false)
    val name: String,
)