@file:Suppress("UnstableApiUsage")

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:actions"))

    implementation(Dependencies.ACTIVITY)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRESCO)
    implementation(Dependencies.MATERIAL)

    api(Dependencies.SWIPE_REFRESH_LAYOUT)
}