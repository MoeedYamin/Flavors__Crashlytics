plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.task_5_flavors__crashlytics"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.task_5_flavors__crashlytics"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    flavorDimensions +="version"

    productFlavors {
        create("staging")
        {
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            applicationId="com.example.task_5_flavors__crashlytics.staging"

        }
        create("production")
        {
            dimension = "version"
            versionNameSuffix = "-production"
            applicationIdSuffix = ".production"
            applicationId="com.example.task_5_flavors__crashlytics.production"

        }
        create("acceptance")
        {
            dimension = "version"
            versionNameSuffix = "-acceptance"
            applicationIdSuffix = ".acceptance"
            applicationId="com.example.task_5_flavors__crashlytics.acceptance"

        }
    }
    buildFeatures {
        viewBinding = true
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-config:21.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("com.google.firebase:firebase-bom:32.4.1"))
    implementation("com.google.firebase:firebase-crashlytics:18.5.1")
    implementation("com.google.firebase:firebase-analytics:21.4.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging:23.3.1")




}