package io.ujon.domain.common.entity

import com.fasterxml.uuid.Generators
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.util.*

@Entity
@Table(name = "document", schema = "ujon")
class Document(
    @Id
    val id: UUID = Generators.timeBasedEpochGenerator().generate(), // uuid version 7
    @Column(name = "type", nullable = false)
    val type: DocumentType,
    @Type(JsonType::class)
    @Column(name = "json_data", columnDefinition = "jsonb")
    var jsonData: Any? = null,
    @Column(name = "text_data", columnDefinition = "text")
    var textData: String? = null,
    @OneToMany(mappedBy = "document", cascade = [CascadeType.ALL])
    var documentTags: Set<DocumentTag> = emptySet()
) : BaseEntity() {

    fun updateTags(tags: Set<Tag>) {
        val updatedTags = tags.map { DocumentTag(document = this, tag = it) }.toSet()
        this.documentTags = updatedTags
    }
}