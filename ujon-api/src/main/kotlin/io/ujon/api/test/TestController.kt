package io.ujon.api.test

import org.hashids.Hashids
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class TestController(
    private val hashids: Hashids
) {

    @GetMapping("/test")
    fun test(): String {
//        val a = Generators.timeBasedEpochGenerator().generate()
//        val hexString = a.toString().replace("-", "");
//        val b = hashids.encodeHex(hexString)
//        val c = hashids.decodeHex(b)
//        val dashString = c.replace("(.{8})(.{4})(.{4})(.{4})(.+)".toRegex(), "$1-$2-$3-$4-$5")
//        val d = UUID.fromString(dashString)
//        println("a: ${a}")
//        println("b: ${b}")
//        println("c: ${c}")
//        println("d: ${d}")
        return ""
    }

    @GetMapping("/test2")
    fun test2() {
        val a = hashids.encode(1, 4, 3)
        val b = hashids.decode(a)
        println(a)
        b.forEach { print("$it, ") }

    }
}