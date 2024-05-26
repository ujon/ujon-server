plugins {
    kotlin("plugin.jpa")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    implementation(project(":common:ujon-util"))

    // data access
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.postgresql:postgresql:42.7.3")
    // querydsl
    api("com.querydsl:querydsl-jpa:5.1.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
    kapt("com.querydsl:querydsl-kotlin-codegen:5.1.0")
}