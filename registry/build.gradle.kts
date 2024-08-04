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
            "Main-Class" to "ru.bakushkin.registry.RegistryApplication"
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
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
