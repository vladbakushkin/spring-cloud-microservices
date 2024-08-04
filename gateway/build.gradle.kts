plugins {
    java
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "ru.bakushkin"
version = "1.0-SNAPSHOT"

// настройка версии Java
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

// репозиторий для скачивания библиотек
repositories {
    mavenCentral()
}

// информация для jar-файла, указание главного main файла
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "ru.bakushkin.gateway.GatewayApplication"
        )
    }
}

// переменная для указания версии spring cloud
extra["springCloudVersion"] = "Hoxton.SR1"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    testImplementation("junit:junit:4.1")
}