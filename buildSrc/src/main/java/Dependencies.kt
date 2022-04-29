import org.gradle.api.JavaVersion

/**
 * @author hoanv
 * @since 10/5/20
 */

object Config {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29
    val javaVersion = JavaVersion.VERSION_1_8
}

object BuildDependenciesVersions {

    const val ACTIVITY = "1.1.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAIN_LAYOUT = "2.0.2"
    const val CORE_KTX = "1.1.0"
    const val COROUTINES = "1.3.3"
    const val FRESCO = "2.3.0"
    const val GOOGLE_SERVICE = "4.3.4"
    const val GRADLE = "4.1.1"
    const val GSON = "2.8.6"
    const val HILT = "1.0.0-alpha02"
    const val HILT_PLUGIN = "2.28.3-alpha"
    const val KOTLIN = "1.3.61"
    const val KOTLIN_GRADLE_PLUGIN = "1.4.20"
    const val MATERIAL = "1.1.0"
    const val MULTIDEX = "1.0.3"
    const val MULTI_STATE_VIEW = "2.2.0"
    const val NAVIGATION = "2.1.0"
    const val NETWORK_RESPONSE = "4.0.1"
    const val OK_HTTP = "4.9.0"
    const val RETROFIT = "2.9.0"
    const val SWIPE_REFRESH_LAYOUT = "1.1.0"
    const val TIMBER = "4.7.1"
}

object Dependencies {

    const val ACTIVITY = "androidx.activity:activity:${BuildDependenciesVersions.ACTIVITY}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAIN_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
    const val FRESCO = "com.facebook.fresco:fresco:${BuildDependenciesVersions.FRESCO}"
    const val GOOGLE_SERVICE_PLUGIN = "com.google.gms:google-services:${BuildDependenciesVersions.GOOGLE_SERVICE}"
    const val GRADLE_BUILD_TOOL = "com.android.tools.build:gradle:${BuildDependenciesVersions.GRADLE}"
    const val GSON = "com.google.code.gson:gson:${BuildDependenciesVersions.GSON}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${BuildDependenciesVersions.HILT_PLUGIN}"
    const val HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${BuildDependenciesVersions.HILT_PLUGIN}"
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${BuildDependenciesVersions.HILT}"
    const val HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.OK_HTTP}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildDependenciesVersions.KOTLIN_GRADLE_PLUGIN}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val MULTIDEX = "com.android.support:multidex:${BuildDependenciesVersions.MULTIDEX}"
    const val MULTI_STATE_VIEW = "com.github.Kennyc1012:MultiStateView:${BuildDependenciesVersions.MULTI_STATE_VIEW}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.OK_HTTP}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_ADAPTER = "com.squareup.retrofit2:adapter-rxjava3:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_RESPONSE = "com.github.haroldadmin:NetworkResponseAdapter:${BuildDependenciesVersions.NETWORK_RESPONSE}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"
    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
}

object AnnotationProcessor {
    const val HILT_ANDROID = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.HILT_PLUGIN}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${BuildDependenciesVersions.HILT}"
}