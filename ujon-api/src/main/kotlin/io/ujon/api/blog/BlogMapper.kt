package io.ujon.api.blog

import io.ujon.api.blog.response.DocumentResponse
import io.ujon.domain.document.dto.spec.DocumentSpec
import org.hashids.Hashids

// to response
fun DocumentSpec.toResponse(hashids: Hashids): DocumentResponse {
    val id = hashids.encodeHex(this.id.toString().replace("-", ""))
    return DocumentResponse(
        id = id,
        type = this.type,
        markdown = this.markdown,
        tags = this.tags
    )
}