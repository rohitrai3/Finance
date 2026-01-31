plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.rohitrai"
version = "0.0.1-SNAPSHOT"
description = "Backend for Finance app"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Lombok
	annotationProcessor("org.projectlombok:lombok:1.18.42")
	compileOnly("org.projectlombok:lombok:1.18.42")

	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	// Lombok
	testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
	testCompileOnly("org.projectlombok:lombok:1.18.42")

	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
