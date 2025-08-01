// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["compose_ui_version"] = "1.4.0"

    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.com.dagger.hilt) apply false
    alias(libs.plugins.com.google.ksp) apply false
    alias(libs.plugins.detekt)
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}