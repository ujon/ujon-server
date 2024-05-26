dependencies {
    implementation(project(":ujon-application"))
    implementation(project(":common:environment-loader"))
    implementation(project(":common:ujon-exception"))
    implementation(project(":common:ujon-config"))

    implementation("org.springframework.boot:spring-boot-starter-security")
}