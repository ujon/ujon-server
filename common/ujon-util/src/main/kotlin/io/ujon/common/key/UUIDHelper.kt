package io.ujon.common.key

import com.fasterxml.uuid.Generators
import java.util.UUID

object UUIDHelper {
    fun v1(): UUID = Generators.timeBasedGenerator().generate()
    fun v6(): UUID = Generators.timeBasedReorderedGenerator().generate()
    fun v7(): UUID = Generators.timeBasedEpochGenerator().generate()
}