plugins {
    id("java")
    id("checkstyle")
}


checkstyle {
    toolVersion = "11.0.1"
    configFile = file("../config/checkstyle/checkstyle.xml")
}


group = "ru.astrosoup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sf.jfastcgi:jfastcgi:2.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}