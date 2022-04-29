@file:Suppress("UnstableApiUsage")

import extensions.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT)
}

android {

    defaultConfig {
        applicationId = "io.github.hoanv810.demo"
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME
        multiDexEnabled = true
    }

    buildTypes {

        getByName(BuildType.DEBUG) {
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    flavorDimensions("demo")
    productFlavors {
        ProductFlavorDevelop.appCreate(this)
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:actions"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":features:news-list"))
    implementation(project(":features:news-detail"))

    implementation(Dependencies.ACTIVITY)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.FRESCO)
    implementation(Dependencies.OK_HTTP)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.RETROFIT_RESPONSE)

    implementation(Dependencies.HTTP_LOGGING)
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_VIEWMODEL)
    kapt(AnnotationProcessor.HILT_ANDROID)
    kapt(AnnotationProcessor.HILT_COMPILER)
}