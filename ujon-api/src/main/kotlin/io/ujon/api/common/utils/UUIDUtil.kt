package io.ujon.api.common.utils

import java.util.*

object UUIDUtil {
    fun trim(uuid: UUID) = uuid.toString().replace("-", "")
    fun uuid(value: String): UUID {
        val uuidString = value.replace("(.{8})(.{4})(.{4})(.{4})(.+)".toRegex(), "$1-$2-$3-$4-$5")
        return UUID.fromString(uuidString)
    }
}