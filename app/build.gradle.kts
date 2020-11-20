
plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-android")
}

android {
    compileSdkVersion(COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(MIN_SDK_VERSION)
        targetSdkVersion(TARGET_SDK_VERSION)

        applicationId = APP_ID
        versionCode = APP_VERSION_CODE
        versionName = APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/main.kotlin_module")
        exclude("META-INF/public_debug.kotlin_module")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(project(":shared"))
    implementation(project(":newsfeed"))
    implementation(project(":player"))
    implementation(project(":di"))
    implementation(project(":repository"))
    implementation(project(":datasource"))
    implementation(project(":networkdatasource"))
    implementation(project(":api"))
    implementAndroidDefaultLibraries()
    implementTestLibraries()
    implementAndroidTestLibraries()
}
