plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.spring") version "2.3.0"
    kotlin("plugin.jpa") version "2.3.0"
    kotlin("plugin.allopen") version "2.3.0"

    war
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))

    implementation(platform("org.springframework:spring-framework-bom:6.2.19"))
    implementation("org.springframework:spring-webmvc")
    implementation("org.springframework:spring-orm")
    implementation("org.springframework:spring-tx")

    implementation("org.springframework.data:spring-data-jpa:3.4.7")
    implementation("org.hibernate.orm:hibernate-core:7.4.7.Final")
    runtimeOnly("org.postgresql:postgresql:42.7.13")
    implementation("org.hibernate.validator:hibernate-validator:8.0.2.Final")

    testImplementation(kotlin("test"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}