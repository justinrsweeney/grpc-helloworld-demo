import com.google.protobuf.gradle.*

plugins {
    java
    `maven-publish`
    idea
    id("com.google.protobuf") version "0.8.17"
}

repositories {
    mavenLocal()

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

var grpcVersion = "1.41.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-auth:${grpcVersion}")
    implementation("io.github.lognet:grpc-spring-boot-starter:4.5.7")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.grpc:grpc-services:${grpcVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
}

group = "com.sweeney"
version = "0.0.1-SNAPSHOT"
description = "grpc-helloworld"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

protobuf {
    protoc {
        // Download from repositories
        artifact = "com.google.protobuf:protoc:3.0.0"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }

    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}
