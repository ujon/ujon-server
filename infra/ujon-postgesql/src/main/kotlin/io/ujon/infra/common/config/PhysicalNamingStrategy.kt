package io.ujon.infra.common.config

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

class PhysicalNamingStrategy : PhysicalNamingStrategyStandardImpl() {
    private val prefix: String = "tbl_"

    override fun toPhysicalTableName(name: Identifier, context: JdbcEnvironment?): Identifier {
        val prefixedTableName: Identifier = convertToSnakeCase(prefix, name)
        return super.toPhysicalTableName(prefixedTableName, context)
    }

    override fun toPhysicalSequenceName(name: Identifier, context: JdbcEnvironment?): Identifier {
        val prefixedSequenceName: Identifier = convertToSnakeCase(prefix, name)
        return super.toPhysicalSequenceName(prefixedSequenceName, context)
    }

    override fun toPhysicalColumnName(name: Identifier, context: JdbcEnvironment?): Identifier {
        val snakeLowerCaseName: Identifier = convertToSnakeCase(name)
        return super.toPhysicalColumnName(snakeLowerCaseName, context)
    }

    // fun
    private fun convertToSnakeCase(name: Identifier): Identifier {
        val regex = "(?<=[a-zA-Z])[A-Z]".toRegex()
        val newName = regex
            .replace(name.text) { "_${it.value}" }
            .lowercase()
        return Identifier(newName, name.isQuoted)
    }

    private fun convertToSnakeCase(prefix: String, name: Identifier): Identifier {
        val regex = "(?<=[a-zA-Z])[A-Z]".toRegex()
        val newName = regex
            .replace(name.text) { "_${it.value}" }
            .lowercase()
        return Identifier(prefix + newName, name.isQuoted)
    }
}