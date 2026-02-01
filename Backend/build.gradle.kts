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
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// Lombok
	annotationProcessor("org.projectlombok:lombok:1.18.42")
	compileOnly("org.projectlombok:lombok:1.18.42")
	// MySQL
	implementation("com.mysql:mysql-connector-j")

	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// Lombok
	testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
	testCompileOnly("org.projectlombok:lombok:1.18.42")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
