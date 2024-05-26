package io.ujon.infra.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["io.ujon.infra"])
@EntityScan(basePackages = ["io.ujon.infra"])
class DBConfig {

    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware { Optional.of("SYSTEM") }
    }
}