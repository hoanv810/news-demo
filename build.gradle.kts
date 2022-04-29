import extensions.applyDefault
import extensions.implementation

plugins {
    id(BuildPlugins.KOTLIN_ANDROID) apply (false)
    id(BuildPlugins.KOTLIN_KAPT) apply (false)
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath(Dependencies.GRADLE_BUILD_TOOL)
        classpath(Dependencies.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.HILT_PLUGIN)
        classpath(Dependencies.GOOGLE_SERVICE_PLUGIN)
    }
}

allprojects {
    repositories.applyDefault()
}

subprojects {

    afterEvaluate {

        plugins.withType<BasePlugin> {
            configure<com.android.build.gradle.BaseExtension> {
                compileSdkVersion(Config.compileSdk)
                defaultConfig {
                    minSdkVersion(Config.minSdk)
                    targetSdkVersion(Config.targetSdk)
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    sourceCompatibility(Config.javaVersion)
                    targetCompatibility(Config.javaVersion)
                }

                if (name != "app") {
                    flavorDimensions("demo")
                    productFlavors {
                        ProductFlavorDevelop.libraryCreate(this)
                    }
                }

                dependencies {
                    implementation(Dependencies.MULTIDEX)
                    implementation(Dependencies.KOTLIN)
                }
            }

            configure<JavaPluginExtension> {
                sourceCompatibility = Config.javaVersion
                targetCompatibility = Config.javaVersion
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
            kotlinOptions { jvmTarget = Config.javaVersion.toString()}
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}