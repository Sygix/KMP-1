plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight") version "2.0.0"
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("io.ktor:ktor-client-core:2.3.6") // core source of ktor
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // For making asynchronous calls
                implementation("io.ktor:ktor-client-core:2.3.6") // core source of ktor
                implementation("io.ktor:ktor-client-content-negotiation:2.3.6") // Simplify handling of content type based deserialization
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6") // make your dataclasses serializable
                implementation("app.cash.sqldelight:runtime:2.0.0") // sqldelight runtime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1") // for datetime

                //Add precompose dependency
                api("moe.tlaster:precompose:1.5.7")
                api(compose.foundation)
                api(compose.animation)

                //Add compose-imageloader library
                api("io.github.qdsfdhvh:image-loader:1.7.1")
                // optional - Moko Resources Decoder
                api("io.github.qdsfdhvh:image-loader-extension-moko-resources:1.7.1")
                // optional - Blur Interceptor (only support bitmap)
                api("io.github.qdsfdhvh:image-loader-extension-blur:1.7.1")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
                implementation("io.ktor:ktor-client-android:2.3.6") // for Android
                implementation("app.cash.sqldelight:android-driver:2.0.0") // sqldelight android driver
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.6") //for iOS
                implementation("app.cash.sqldelight:native-driver:2.0.0") // sqldelight native driver
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("io.ktor:ktor-client-apache:2.3.6") // for Desktop
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0") // sqldelight sqlite driver
                api("io.github.qdsfdhvh:image-loader-extension-imageio:1.7.1")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.myapplication.database")
            listOf("sqldelight")
        }
    }
}