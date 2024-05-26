val jsonwebtokenVersion: String by project

dependencies {
    implementation(project(":ujon-domain"))
    implementation(project(":common:ujon-exception"))

    // application: auth
    implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
    implementation("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jsonwebtokenVersion")
    implementation("org.springframework.security:spring-security-crypto")
}