package io.ujon.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "io.ujon.api",
        "io.ujon.application",
        "io.ujon.common",
        "io.ujon.domain",
        "io.ujon.infra"
    ],
)
@ConfigurationPropertiesScan(basePackages = ["io.ujon"])
class UjonApiServer

fun main(args: Array<String>) {
    runApplication<UjonApiServer>(*args)
}