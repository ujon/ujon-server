tasks.jar { enabled = true }
tasks.bootJar { enabled = false }

plugins {
    kotlin("plugin.jpa")
    kotlin("kapt")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    // module
    implementation(project(":ujon-core"))
    // database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
    implementation("org.postgresql:postgresql:42.6.0")
    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-kotlin-codegen:5.0.0")

    // functional
    implementation("com.fasterxml.uuid:java-uuid-generator:4.2.0") // https://www.baeldung.com/java-generating-time-based-uuids

}