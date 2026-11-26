plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.cyclonedx)
    application
    `maven-publish`
}

group = "org.example"
version = providers.environmentVariable("TAG_NAME")
    .map { it.removePrefix("v") }
    .getOrElse("1.0.1")

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
}

dependencies {
    implementation(libs.kotlin.logging)
    runtimeOnly(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.kotlin.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

application {
    mainClass.set("org.example.MainKt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}