plugins {
    java
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.3"
    id("io.freefair.lombok") version "8.0.1"
}

group = "ru.bakushkin"
version = "1.0-SNAPSHOT"

// репозиторий для скачивания библиотек
repositories {
    mavenCentral()
}

// информация для jar-файла, указание главного main файла
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "ru.bakushkin.deposit.DepositApplication"
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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    implementation("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
    testImplementation("org.mockito:mockito-core:3.2.4")
    testImplementation("org.assertj:assertj-core:2.2.0")
    testImplementation("org.testng:testng:7.7.0")
    testImplementation("org.jmockit:jmockit:1.49")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}

tasks.withType<Test> {
    useJUnitPlatform()
}