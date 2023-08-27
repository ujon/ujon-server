package io.ujon.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UjonServerApplication

fun main(args: Array<String>) {
    runApplication<UjonServerApplication>(*args)
}
