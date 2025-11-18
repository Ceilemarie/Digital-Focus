plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.example.digitalfocus"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.digitalfocus"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations.all {
    resolutionStrategy {
        //
        eachDependency {
            if (requested.group == "androidx.core" && requested.name == "core-ktx") {
                // Force all dependencies pulling core-ktx to use the known stable version.
                useVersion("1.12.0")
            }
        }
    }
}

dependencies {

    // Keep only ONE instance of this. ADD THE EXCLUSION BLOCK.
    implementation("androidx.activity:activity-compose:1.8.2") {
        //  CRITICAL: Exclude the Material 2 library that causes the conflict
        exclude(group = "androidx.compose.material", module = "material")
    }

    implementation("androidx.compose.ui:ui"){
        exclude(group = "androidx.compose.material", module = "material")
    }

    implementation("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:8.9.1")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    // REQUIRED FOR THEME (AppCompat/Material Components)
    implementation("androidx.appcompat:appcompat:1.6.1") // Or the latest stable version
    implementation("com.google.android.material:material:1.11.0") // Or the latest stable version
    //  Jetpack Compose BOM and UI
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.activity:activity-compose:1.8.2")

    }

