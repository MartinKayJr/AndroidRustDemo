plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.rust.android)
}

android {
    namespace = "cn.martinkay.androidrustdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "cn.martinkay.androidrustdemo"
        minSdk = 24
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

cargo {
    module = "../rust"       // Or whatever directory contains your Cargo.toml
    libname = "rust"          // Or whatever matches Cargo.toml's [package] name.
    targets = listOf("arm", "arm64", "x86")  // See bellow for a longer list of options
    profile = "release" // Or "debug" if you want to debug your Rust code
}

tasks.matching { it.name.matches(Regex("merge.*JniLibFolders")) }.configureEach {
    inputs.dir(File(buildDir, "rustJniLibs/android"))
    dependsOn("cargoBuild")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}