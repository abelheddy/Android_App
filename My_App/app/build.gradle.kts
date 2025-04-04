plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.RCS.my_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.RCS.my_app"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"

    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //implementaciones
    implementation(libs.navigation.compose)
    implementation (libs.ui)
    implementation (libs.androidx.compose.material3.material33)
    implementation (libs.androidx.compose.ui.ui.tooling.preview)
    implementation (libs.androidx.activity.compose)
    implementation(libs.coil.kt.coil.compose)
    //implementation(libs.androidx.storage)
    implementation(libs.konfetti.compose)
    implementation(libs.androidx.compose.material.material.icons.extended2)
    implementation(libs.androidx.compose.material.material.icons.extended2)
    implementation(libs.androidx.compose.material3.material33)
    implementation(libs.androidx.compose.material.material.icons.extended2)
    implementation(libs.coil.kt.coil.compose)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.compose.material3.material33)
    implementation(libs.coil.kt.coil.compose)
    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.material33)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // Coil for images
    implementation(libs.coil.kt.coil.compose)

    // Material icons extended
    implementation(libs.androidx.compose.material.material.icons.extended2)

    // Navigation Compose
    implementation(libs.navigation.compose)

}