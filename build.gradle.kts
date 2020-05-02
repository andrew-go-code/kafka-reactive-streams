import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

allprojects {

    group = "demo"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

subprojects {

    repositories {
        mavenCentral()
    }

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")

        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.kafka:spring-kafka")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.github.microutils:kotlin-logging:1.7.9")

    }

//
//    dependencies {
////        implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
////        implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
//        implementation("org.springframework.boot:spring-boot-starter-web")
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//        implementation("org.apache.kafka:kafka-streams")
//        implementation("org.jetbrains.kotlin:kotlin-reflect")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//        implementation("org.springframework.kafka:spring-kafka")
//        testImplementation("org.springframework.boot:spring-boot-starter-test") {
//            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//        }
//        testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
//        testImplementation("io.projectreactor:reactor-test")
//        testImplementation("org.springframework.kafka:spring-kafka-test")
//    }
}
