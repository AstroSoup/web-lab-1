plugins {
    id("java")
    id("checkstyle")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


checkstyle {
    toolVersion = "11.0.1"
    configFile = file("../config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}


group = "ru.astrosoup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


application {
    mainClass.set("ru.astrosoup.Main")
}
dependencies {
    implementation(files("lib/fastcgi-lib.jar"))
    implementation("com.fasterxml.jackson.core:jackson-databind:latest.release")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:latest.release")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}