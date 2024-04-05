rootProject.name = "ok-marketplace-be"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../build-plugin")
    plugins {
        id("build-jvm") apply false
        id("build-kmp") apply false
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

include(":ok-marketplace-api-v1-jackson")
include(":ok-marketplace-api-v1-mappers")
include(":ok-marketplace-api-v2-kmp")
include(":ok-marketplace-api-log1")

include(":ok-marketplace-common")
include(":ok-marketplace-biz")
include(":ok-marketplace-stubs")

include(":ok-marketplace-app-common")
//include(":ok-marketplace-app-tmp")
include(":ok-marketplace-app-spring")
include(":ok-marketplace-app-ktor")
include(":ok-marketplace-app-rabbit")
include(":ok-marketplace-app-kafka")

