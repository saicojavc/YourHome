plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
//    alias(libs.plugins.com.dagger.hilt)
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.saico.yourhome"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.saico.yourhome"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    android.applicationVariants.configureEach {
        //Example (com.saico.airlineticket-v0.0.1-release.apk)
        val name = buildType.name // buildType [debug,release]

        outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach {
                it.outputFileName = "${applicationId}-v${versionName}-${name}.apk"
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
            isMinifyEnabled = false
            isShrinkResources = false
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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
//    implementation(project(":feature:seat"))
//    implementation(project(":feature:ticket_detail"))

    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.compose.ktx)
    coreLibraryDesugaring(libs.com.android.tools.desugar)

//    firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth.ktx)

    //SplashScreen
    implementation(libs.androidx.core.core.splashscreen)

    //hilt
    implementation(libs.com.google.dagger.hilt.android)
//    ksp(libs.com.google.dagger.hilt.compiler)

    implementation(libs.androidx.constraintlayout.compose)

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

    implementation(libs.kotlin.stdlib)
    implementation(libs.io.coil.kt)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.pager)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.glide)
    implementation("com.google.code.gson:gson:2.11.0")
    implementation(libs.androidx.foundation)
    implementation(libs.accompanist.systemuicontroller)
}