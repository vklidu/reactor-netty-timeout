plugins {
  kotlin("jvm") version "2.1.20"
  id("org.springframework.boot") version "3.5.0"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("io.projectreactor.netty:reactor-netty-http")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.kotest:kotest-assertions-core:5.8.1")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}