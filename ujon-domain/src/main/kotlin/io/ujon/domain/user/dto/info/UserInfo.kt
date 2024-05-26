package io.ujon.domain.user.dto.info

import java.util.*

/**
 * Represents a user's basic information.
 *
 * @property userId Unique identifier for the user.
 * @property username User's nickname.
 * @property name User's real name.
 * @property email User's primary email address.
 */
data class UserInfo(
    val userId: UUID,
    val username: String,
    val name: String,
    val email: String,
)
