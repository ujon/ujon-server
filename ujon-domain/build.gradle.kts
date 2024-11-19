val jsonwebtokenVersion: String by project

dependencies {
    implementation(project(":common:ujon-exception"))
    implementation(project(":common:ujon-util"))
    implementation(project(":infra:ujon-postgesql"))

    implementation("org.springframework.security:spring-security-crypto")
}