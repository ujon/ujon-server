package io.ujon.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "io.ujon.*"
    ]
)
class UjonApi

fun main(args: Array<String>) {
    runApplication<UjonApi>(*args)
}