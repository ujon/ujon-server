package io.ujon.common.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {
    /**
     * describe:
     * 1. set property naming strategy to snake case
     * 2. register java time module
     */
    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .registerModule(JavaTimeModule())
//        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}