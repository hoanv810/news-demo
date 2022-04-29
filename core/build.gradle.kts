import extensions.api

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT)
}

dependencies {
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_VIEWMODEL)

    implementation(Dependencies.OK_HTTP)
    implementation(Dependencies.RETROFIT_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.RETROFIT_RESPONSE)
    implementation(Dependencies.HTTP_LOGGING)
    api(Dependencies.GSON)
    api(Dependencies.TIMBER)

    kapt(AnnotationProcessor.HILT_ANDROID)
    kapt(AnnotationProcessor.HILT_COMPILER)
}