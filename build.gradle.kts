import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val junitJupiterVersion = "5.4.2"

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
	// https://mvnrepository.com/artifact/mysql/mysql-connector-java
	implementation("mysql:mysql-connector-java:8.0.27")
	// https://mvnrepository.com/artifact/org.springframework.retry/spring-retry
	implementation("org.springframework.retry:spring-retry:1.3.1")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.2.0")

	// テストでコンテナを使用する
	testImplementation("org.testcontainers:mysql:1.16.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
	testImplementation("org.testcontainers:testcontainers:1.16.2")
	testImplementation("org.testcontainers:junit-jupiter:1.16.2")
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
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	languageVersion = "1.5"
}