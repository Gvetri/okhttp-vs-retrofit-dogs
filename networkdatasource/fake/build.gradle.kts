import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

version = LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(project(":model"))
    implementation(project(":datasource"))
    implementation(project(":apimodel"))
    implementation(project(":api"))
    implementation(project(":api:fakeapi"))
    implementation(KOIN_CORE)
    implementation(KOTLIN_SERIALIZATION)
    implementation(RETROFIT)
    implementation(KOTLIN_SERIALIZATION_ADAPTER)
    implementation(MOCK_RETROFIT)
    implementation(ARROW_CORE)
    implementation(OKHTTP)
    implementation(ARROW_CORE)
    implementation(OKHTTP_LOG_INTERCEPTOR)
    implementation(COROUTINES)
    testImplementation(COROUTINES_TEST)
    kapt(ARROW_SYNTAX)
    implementTestLibraries()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
