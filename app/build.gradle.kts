import java.io.File
import java.io.FileInputStream
import java.util.Properties

// Function to read properties from local.properties file
fun gradleLocalProperties(projectRootDir: File, providers: org.gradle.api.provider.ProviderFactory): Properties {
    val props = Properties()
    val localPropertiesFile = File(projectRootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { props.load(it) }
    }
    return props
}
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.fahim.geminiapistarter"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.fahim.geminiapistarter"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val props = gradleLocalProperties(rootDir, providers)
        val geminiKey = props.getProperty("GEMINI_API_KEY") ?: ""
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Room schema export directory
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.common)
    implementation("com.google.android.material:material:1.12.0")

    // Gemini AI - Updated to use the new Google GenAI SDK
    implementation(libs.generativeai)
    implementation("com.google.guava:guava:33.2.1-android")

    // RecyclerView for chat interface
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Room database dependencies - Updated to latest stable versions
    implementation("androidx.room:room-runtime:2.7.2")
    annotationProcessor("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-guava:2.7.2")

    // ViewModel and LiveData - Updated versions
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.9.0-alpha08")
    implementation("androidx.lifecycle:lifecycle-livedata:2.9.0-alpha08")

    // For concurrent operations with Room - Updated version
    implementation("androidx.concurrent:concurrent-futures:1.2.0")

    // Material Components - Latest stable version
    implementation("com.google.android.material:material:1.12.0")

    // Fragment support - Updated version
    implementation("androidx.fragment:fragment:1.8.4")

    // JSON parsing - Updated to latest stable version
    implementation("com.google.code.gson:gson:2.11.0")

    // Testing dependencies
    testImplementation(libs.junit)
    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("androidx.room:room-testing:2.7.2")

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.6.1")
    androidTestImplementation("androidx.room:room-testing:2.7.2")
}
