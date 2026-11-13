plugins {
    kotlin("jvm")
    application
    `maven-publish`
}

group = "org.example"
version = providers.environmentVariable("TAG_NAME")
    .map { it.removePrefix("v") }
    .getOrElse("1.0.0")

kotlin {
    jvmToolchain(25)
}

java {
    withSourcesJar()
}

dependencies {
    testImplementation(kotlin("test"))
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