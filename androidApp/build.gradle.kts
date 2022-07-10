plugins {
    id(androidApp)
    kotlin(androidPlugin)
    kotlin(kapt)
}

android {
    compileSdk = Versions.compilerSdk
    defaultConfig {
        applicationId = "com.github.ryutaro.andocchi_kmm.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.material3.windowSizeClass)
    implementation(libs.material3.core)
    implementation(libs.material.icon)
    implementation(libs.androidx.navigation.compose)

    androidTestImplementation(libs.androidx.ui.test.junit)
    androidTestImplementation(libs.junit.test)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.turbine)
}