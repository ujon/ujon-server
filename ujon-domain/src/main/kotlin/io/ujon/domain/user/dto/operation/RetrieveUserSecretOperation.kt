package io.ujon.domain.user.dto.operation

class RetrieveUserSecretOperation {
    data class Email(
        val email: String,
    )

    data class Username(
        val username: String,
    )
}
