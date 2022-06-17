@file:Suppress("LocalVariableName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.springdoc.openapi-gradle-plugin") version "1.3.4"
	kotlin("jvm") version "1.7.0"
	kotlin("plugin.serialization") version "1.7.0"
	kotlin("plugin.spring") version "1.7.0"
	kotlin("plugin.jpa") version "1.7.0"
	kotlin("kapt") version "1.7.0"
	java
}

group = "github.mewgrammer"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

extra["testcontainersVersion"] = "1.17.2"

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-graphql")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.apache.logging.log4j:log4j-api-kotlin:1.1.0")
	implementation("org.flywaydb:flyway-core")

	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.hamcrest:hamcrest:2.2")

    val springDocVersion = "1.6.9"
	implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
	implementation("org.springdoc:springdoc-openapi-webmvc-core:$springDocVersion")
	implementation("org.springdoc:springdoc-openapi-security:$springDocVersion")

	val mapstructVersion = "1.5.1.Final"
	implementation("org.mapstruct:mapstruct:$mapstructVersion")
	kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")


	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	ext["mockito.version"] = "4.6.1" // mockito version is manage by spring-boot-starter-test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
}

tasks.processResources {
	expand(project.properties)
}

openApi {
	val management_port: String by project
	apiDocsUrl.set("https://localhost:${management_port}/api-docs")
	outputDir.set(file("$buildDir/docs"))
	outputFileName.set("swagger.json")
	waitTimeInSeconds.set(10)
}

allOpen {
	annotation("javax.persistence.Entity")
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
		arg("mapstruct.unmappedTargetPolicy", "IGNORE")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
