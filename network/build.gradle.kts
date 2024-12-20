plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.network"
    compileSdk = libs.versions.compileSDK.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSDK.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    flavorDimensions += "environment"
    productFlavors {
        create("Production") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "${properties["BASE_URL"]}")
            buildConfigField("String", "POSTER_URL", "${properties["POSTER_URL"]}")
            buildConfigField("String", "API_KEY", "${properties["API_KEY"]}")
        }
        create("Staging") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "${properties["BASE_URL"]}")
            buildConfigField("String", "POSTER_URL", "${properties["POSTER_URL"]}")
            buildConfigField("String", "API_KEY", "${properties["API_KEY"]}")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kapt(libs.hilt.android.compiler)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.retrofit)
}