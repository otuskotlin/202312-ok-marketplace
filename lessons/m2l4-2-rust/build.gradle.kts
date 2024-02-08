import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

plugins {
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    listOf(
        linuxX64(),
//        macosArm64(),
    ).forEach {
        it.apply {
            compilations.getByName("main") {
                cinterops {
                    // настраиваем cinterop в файле src/nativeInterop/cinterop/rs_example.def
                    create("rs_example")
                }
            }
            binaries {
                executable {
                    entryPoint = "main"
                }
            }
        }
    }
    val coroutinesVersion: String by project
    val datetimeVersion: String by project

    // Description of modules corresponding to our target platforms
    //  common - common code that we can use on different platforms
    //  for each target platform, we can specify our own specific dependencies
    sourceSets {
        nativeMain {
        }
        nativeTest {
        }

    }
}

tasks {
    withType(KotlinNativeTest::class) {
        val pathToLib: String = project.layout.projectDirectory.dir("rs-example/target/debug").toString()
        println("PATH TO LIB: $pathToLib")
        environment("LD_LIBRARY_PATH", pathToLib)
    }
}
