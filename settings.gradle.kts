rootProject.name = "ujon-server"

include(
    /* Application */
    "ujon-api",
    "ujon-application",
    "ujon-domain",

    /* Common */
    "common:environment-loader",
    "common:ujon-config",
    "common:ujon-exception",
//    "common:ujon-type",
    "common:ujon-util",

    /* Infra */
    "infra:ujon-postgesql",
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    resolutionStrategy.eachPlugin {
        when (requested.id.id) {
            "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
            "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
            "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
            "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
            "org.springframework.boot" -> useVersion(springBootVersion)
            "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
        }
    }
}