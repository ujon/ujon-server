package io.ujon.core.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JsonUtil {
    private val objectMapper = ObjectMapper().registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .registerModule(JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

    fun jsonString(vararg args: Pair<String, Any?>): String = objectMapper.writeValueAsString(args.toMap())
    fun jsonString(obj: Any): String = objectMapper.writeValueAsString(obj)
}