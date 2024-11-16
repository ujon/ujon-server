import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("kapt")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}


tasks.bootJar { enabled = false }
tasks.jar { enabled = false }

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

allprojects {
    val projectGroup: String by project
    val applicationVersion: String by project
    group = projectGroup
    version = applicationVersion
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        // mapping
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.mockito", module = "mockito-core")
        }
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.1")
        testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")
        testImplementation("io.mockk:mockk:1.13.13")
        testImplementation("com.ninja-squad:springmockk:4.0.2")
    }

    tasks.bootJar { enabled = false }
    tasks.jar { enabled = true }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
        useJUnitPlatform()
    }
}
