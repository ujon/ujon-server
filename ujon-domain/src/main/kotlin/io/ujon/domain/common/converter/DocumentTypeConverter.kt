package io.ujon.domain.common.converter

import io.ujon.domain.common.entity.DocumentType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class DocumentTypeConverter : AttributeConverter<DocumentType, String> {
    override fun convertToDatabaseColumn(attribute: DocumentType): String {
        return attribute.value()
    }

    override fun convertToEntityAttribute(dbData: String): DocumentType? {
        return DocumentType.fromValue(dbData)
    }
}