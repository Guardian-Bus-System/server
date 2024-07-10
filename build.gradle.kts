import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.4"
  id("io.spring.dependency-management") version "1.1.4"
  kotlin("jvm") version "1.9.23"
  kotlin("plugin.spring") version "1.9.23"
  kotlin("plugin.jpa") version "1.9.23"
}

group = "com.gachi"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-websocket")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  //심각도가 높은 취약점 발견, 인증서 검사 취약점 발견
  implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
  implementation("io.jsonwebtoken:jjwt-api:0.11.5")
  implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
  implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  //
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  //누리고 서비스
  implementation("net.nurigo:sdk:4.3.0")

  runtimeOnly("com.mysql:mysql-connector-j")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")

  //Swagger 적용
//  implementation("io.springfox:springfox-boot-starter:3.0.0")
//  implementation("io.springfox:springfox-swagger2:3.0.0")
//  implementation("io.springfox:springfox-swagger-ui:3.0.0")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
//  implementation("io.springfox:springfox-boot-starter:3.0.0")
//  implementation("io.springfox:springfox-swagger-ui:3.0.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
