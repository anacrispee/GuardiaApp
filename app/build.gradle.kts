plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.serializarion)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.guardia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.guardia"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {

    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.lottie)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil)
    implementation(platform(libs.okhttp3))
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.interceptor)
    implementation(libs.squareup.okhttp.retrofit)
    implementation(libs.squareup.okhttp.retrofit.gson.converter)
    implementation(libs.android.koin)
    implementation(libs.android.koinCore)
    implementation(libs.android.koinCompose)
    implementation(libs.navigation)
    implementation(libs.splashscreen)
    implementation(libs.mockito)
    implementation(libs.testimplementation)
    implementation(libs.coretesting)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}