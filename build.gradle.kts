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
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        // mapping
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
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
        useJUnitPlatform()
    }
}