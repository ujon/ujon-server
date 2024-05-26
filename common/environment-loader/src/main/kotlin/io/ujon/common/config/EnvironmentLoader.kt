package io.ujon.common.config

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.context.properties.bind.Bindable
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver
import org.springframework.boot.context.properties.source.ConfigurationPropertySources
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.Ordered
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.Profiles
import org.springframework.core.env.PropertySource
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.util.*

class EnvironmentLoader : EnvironmentPostProcessor, Ordered {
    private val log = LoggerFactory.getLogger(javaClass)
    private val defaultOrder = Ordered.LOWEST_PRECEDENCE
    private val springProfiles = "spring.config.activate.on-profile"
    private val yamlLoader = YamlPropertySourceLoader()
    private val prefix = "application-"
    private val extensions = listOf(".yml", ".yaml")
    private val resourcePattern = "classpath*:$prefix*{${extensions.joinToString(",")}}"

    override fun postProcessEnvironment(environment: ConfigurableEnvironment?, application: SpringApplication?) {
        try {
            val resourceLoader = application?.resourceLoader ?: DefaultResourceLoader()
            val resources = PathMatchingResourcePatternResolver(resourceLoader)
                .getResources(resourcePattern)

            resources.forEach { resource ->
                loadYaml(resource, environment!!).forEach { propertySource ->
                    environment.propertySources.addLast(propertySource)
                }
            }
            display(environment!!)
        } catch (e: Exception) {
            log.error("# [EnvironmentLoader] message: {}, stacktrace: {}", e.message, e.stackTrace)
        }
    }


    override fun getOrder(): Int {
        return defaultOrder
    }

    // function
    private fun loadYaml(path: Resource, environment: ConfigurableEnvironment): List<PropertySource<*>> {
        val defaultPropertySource = mutableListOf<PropertySource<*>>()
        val propertySourceList = yamlLoader.load(path.filename, path)
            .filter { propertySource ->
                val binder = Binder(
                    ConfigurationPropertySources.from(propertySource),
                    PropertySourcesPlaceholdersResolver(environment)
                )
                binder.bind(springProfiles, Bindable.of(Array<String>::class.java))
                    .orElseGet {
                        defaultPropertySource.add(propertySource);
                        emptyArray()
                    }
                    .any {
                        environment.acceptsProfiles(Profiles.of(it))
                    }
            }

        return propertySourceList + defaultPropertySource
    }

    private fun display(environment: ConfigurableEnvironment) {
        val properties = environment.propertySources
            .filter { it.name.contains(prefix) }
            .map { it.name.replace(prefix, "").substringBefore(" ") }
            .distinct()
            .joinToString(separator = ", ")
        println("Imported properties: $properties\n")
    }
}