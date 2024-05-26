package io.ujon.application.user.dto.output

import java.util.*


data class UserSecretOutput(
    val userId: UUID,
    val role: String?,
)