version = LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    kotlin(SERIALIZATION_PLUGIN) version KOTLIN
    kotlin("kapt")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(project(":datasource"))
    implementation(project(":model"))
    implementation(project(":apimodel"))
    implementation(project(":api"))
    implementation(KOTLIN_SERIALIZATION)
    implementation(RETROFIT)
    implementation(KOTLIN_SERIALIZATION_ADAPTER)
    implementation(KOIN_CORE)
    implementation(OKHTTP)
    implementation(OKHTTP_LOG_INTERCEPTOR)
    implementation(COROUTINES)
    implementation(ARROW_CORE)
    implementation(MOCK_RETROFIT)
    kapt(ARROW_SYNTAX)
    testImplementation(project(":networkdatasource:fake"))
    testImplementation(project(":api:fakeapi"))
    implementTestLibraries()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
