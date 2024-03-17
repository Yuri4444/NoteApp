plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.yuri_berezhnoy.noteapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yuri_berezhnoy.noteapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //RxJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    //Dagger2
//    implementation(libs.android.hilt)
//    annotationProcessor(libs.android.hilt.compiler)

    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    annotationProcessor(libs.dagger.android.processor)
}