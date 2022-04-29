plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    maven { url = uri("https://jitpack.io") }
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.1.1"
    const val GRADLE_VERSIONS = "0.34.0"
    const val KOTLIN = "1.3.61"
    const val NAVIGATION = "2.1.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
}