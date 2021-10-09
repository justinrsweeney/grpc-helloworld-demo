var grpcVersion = "1.41.0"
var springVersion = "2.5.5"

plugins {
    java
    id("org.springframework.boot") version "2.5.5"
}

group = "com.sweeney"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":grpc-helloworld-common"))
    implementation("org.springframework.boot:spring-boot-starter-actuator:${springVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springVersion}")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-auth:${grpcVersion}")
    implementation("io.github.lognet:grpc-spring-boot-starter:4.5.7")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.grpc:grpc-services:${grpcVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springVersion}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}