version = ANDROID_LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdkVersion(COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(MIN_SDK_VERSION)
        targetSdkVersion(TARGET_SDK_VERSION)

        versionCode = LIBRARY_VERSION_CODE
        versionName = LIBRARY_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(project(":shared"))
    implementation(project(":repository:public"))
    implementation(project(":di"))
    implementation(project(":model"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("com.dailymotion.dailymotion-sdk-android:sdk:0.2.5")
    implementAndroidDefaultLibraries()
    implementTestLibraries()
    implementAndroidTestLibraries()
    implementation(ARROW_CORE)
    implementation(COROUTINES)
    implementation(COIL)
    kapt(ARROW_SYNTAX)
    testImplementation(project(":repository:fake"))
    testImplementation(project(":networkdatasource:fake"))
    testImplementation(project(":featurea:fake"))
    testImplementation(COROUTINES_TEST)
    testImplementation(ANDROIDX_CORE_TESTING)
}
