plugins {
    kotlin(multiplatform)
    kotlin(cocoapods)
    kotlin(serializationPlugin) version Versions.kotlin
    id(androidLibrary)
    id(sqldelightPlugin)
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // ktor
                implementation(libs.ktor.core)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.content.negotiation)

                // koin
                implementation(libs.koin.core)

                // serialization
                implementation(libs.serialization)

                // napier
                implementation(libs.napier)

                // coroutine
                implementation(libs.coroutine.core)

                // sqldelight
                implementation(libs.sqldelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                // koin
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                // ktor
                implementation(libs.ktor.android)

                // coroutine
                implementation(libs.coroutine.android)

                // sqldelight
                implementation(libs.sqldelight.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.coroutine.test)
                implementation(libs.mockk)
                implementation(libs.junit.test)
                implementation(libs.turbine)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // ktor
                implementation(libs.ktor.darwin)

                // sqldelight
                implementation(libs.sqldelight.native)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = Versions.compilerSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.github.ryutaro.andocchi_kmm.db"
    }
}