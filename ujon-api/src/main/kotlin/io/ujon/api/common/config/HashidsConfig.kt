package io.ujon.api.common.config

import org.hashids.Hashids
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class HashidsConfig {
    @Bean
    fun hashids(): Hashids {
        return Hashids("thisissparta", 12) // 8 is the minimum hash length
    }
}