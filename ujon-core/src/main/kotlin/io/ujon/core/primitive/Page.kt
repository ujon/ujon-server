package io.ujon.core.primitive

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Page<T>(
    val content: List<T>,               // 데이터
    val size: Int? = null,                      // 페이지 당 나타낼 수 있는 데이터 수
    val numberOfElements: Int? = null,          // 실제 데이터 수
    val totalPages: Int? = null,                // 전체 페이지 수
    val totalElements: Long? = null,             // 전체 데이터 수
    val number: Int? = null,                    // 현재 페이지 번호
    val isLast: Boolean? = null,                // 마지막 페이지 인지
    val isFirst: Boolean? = null,               // 첫번째 페이지 인지
    val isEmpty: Boolean? = null                // 리스트가 비어 있는지
)
