plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
//    implementation("io.ktor:ktor-server-netty:2.3.7")
//    implementation("io.ktor:ktor-server-core:2.3.7")
//    implementation("io.ktor:ktor-gson:2.3.7")
//    implementation("io.ktor:ktor-client-gson:2.3.7")

//    implementation("ch.qos.logback:logback-classic:")

//    testImplementation("io.ktor:ktor-server-tests:2.3.7")
    testImplementation("io.kotest:kotest-framework-engine:5.8.0")
    testImplementation("org.testcontainers:testcontainers:1.17.6")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.35.1")
    testImplementation(kotlin("test"))
}

application {
//    mainClassName = "your.package.name.ApplicationKt" // Adjust the package and main class name
}

//tasks.test {
//    useJUnitPlatform()
//}
