version = "0.0.1"


plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2" // rest docs #1
}
val asciidoctorExtensions by configurations.creating  // rest docs #2
var snippetsDir = file("build/generated-snippets") // rest docs #3


dependencies {
    // module
    implementation(project(":ujon-core"))
    implementation(project(":ujon-domain"))
    // spring
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // docs
    asciidoctorExtensions("org.springframework.restdocs:spring-restdocs-asciidoctor")  // rest docs #4
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")  // rest docs #5
    //
    implementation("org.hashids:hashids:1.0.3")
}

// Spring REST Docs config
tasks.test {
    useJUnitPlatform()
    outputs.dir(snippetsDir)
}
tasks.asciidoctor {
    doFirst { delete(file("src/main/resources/static/docs")) }
    dependsOn(tasks.test)
    configurations("asciidoctorExtensions")
    baseDirFollowsSourceFile()
    inputs.dir(snippetsDir)
}

tasks.register("copyDocument", Copy::class) {
    dependsOn(tasks.asciidoctor)
    from(file("build/docs/asciidoc"))
    into(file("src/main/resources/static/docs"))
}
tasks.build {
    dependsOn(tasks.getByName("copyDocument"))
}
tasks.bootJar {
    dependsOn(tasks.getByName("copyDocument"))
}
tasks.jar {
    archiveClassifier.set("")
}