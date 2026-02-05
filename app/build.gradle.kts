plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("androidx.room")
    id ("org.sonarqube")

}

android {
    namespace = "com.rishabh.e_commerceapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rishabh.e_commerceapp"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

}

//sonarqube {
//    properties {
//        property ("sonar.projectKey", "rishabhparihar77")
//        property ("sonar.organization", "rishabhparihar77") // Optional for SonarCloud
//        property ("sonar.host.url", "https://sonarcloud.io")  // URL of your SonarQube server
//        property ("sonar.projectName", "rishabh_e_commerce_app")
//            property ("sonar.token", "2996ab54d274cc8d18f428fa81e493583b80250e")
//        property ("sonar.projectVersion", "1.0")
//        property ("sonar.sources", "src/main/java,src/main/kotlin")
//        property ("sonar.tests", "src/test/java,src/androidTest/java")
//        property ("sonar.java.binaries", "${layout.buildDirectory}/classes")
//        property ("sonar.exclusions",
//        "**/R.class," +
//                "**/R$*.class," +
//                "**/BuildConfig.*," +
//                "**/Manifest*.*," +
//                "**/*Test*.*," +
//                "**/android/**/*")
//        property ("sonar.kotlin.detekt.reportPaths",
//        "build/reports/detekt/detekt.xml")
//
//    }
//}



sonar {
    properties {
        property ("sonar.projectKey", "rishabhparihar77_rishabh_e_commerce_app")
        property ("sonar.organization", "rishabhparihar77")
        property ("sonar.host.url", "https://sonarcloud.io")

        property ("sonar.sources", listOf("app/src/main/java","app/src/main/kotlin"))

        property ("sonar.tests", listOf("app/src/test/java"))

        property ("sonar.exclusions", listOf(
            "**/R.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "**/androidTest/**"
        )
        )
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

    // Hilt Dependency
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-compiler:2.56.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Check for the latest version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Viewmodel Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // Check for the latest version
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.1") // Check for the latest version
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")


    // For LifecycleScope (e.g., in Activities/Fragments)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Navigation Component
    implementation("androidx.navigation:navigation-compose:2.7.6")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Choose a converter (e.g., Gson, Moshi)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // For Gson
    // Or implementation("com.squareup.retrofit2:converter-moshi:2.9.0") // For Moshi
    // Optional: For logging HTTP requests and responses (very useful for debugging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Room DB
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    // Kotlin Extensions and Coroutines support for Room (highly recommended)
    implementation("androidx.room:room-ktx:$roomVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

}