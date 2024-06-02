plugins {
    kotlin("jvm") version "1.9.21"
}

group = "ru.otus.otuskotlin.ml"
version = "0.0."

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.microsoft.onnxruntime:onnxruntime:1.16.3")
    implementation("ai.djl.huggingface:tokenizers:0.25.0")
    implementation("org.slf4j:slf4j-api:2.0.9")

    testImplementation(kotlin("test-junit5"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}

kotlin {
    javaToolchains {
        javadocToolFor {
            languageVersion.set(JavaLanguageVersion.of(17))
        }

    }
}
