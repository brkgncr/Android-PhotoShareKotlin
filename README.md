# PhotoShare - Android Application

PhotoShare is a photo-sharing application designed for the Android platform using Kotlin. This app allows users to create an account, log in, and share photos with comments, creating an engaging feed experience for all users.

---

## Features

- **User Authentication**: Users can sign up or log in using Firebase Authentication.
- **Photo Feed**: A scrollable feed displaying all shared posts with photos and comments.
- **Photo Upload**: Users can upload images from their gallery, add comments, and share them on the feed.
- **Logout Option**: Easy logout functionality to secure user accounts.
- **Real-time Updates**: The feed updates in real-time using Firebase Firestore.

---

## Screens and Functionality

### Login/Sign-Up Screen
- Users can log in or create an account on the same screen.
- Firebase Authentication handles user credentials securely.

### Feed Screen
- Displays a list of shared posts with photos and comments.
- Fetches posts in descending order by date.

### Floating Action Button Menu
- Provides options to upload a new post or log out.

### Photo Upload Screen
- Allows users to select an image from their device gallery.
- Users can add a comment and share the post on the feed.

---

## Technologies Used

- **Programming Language**: Kotlin
- **Frameworks and Libraries**:
  - Firebase Authentication
  - Firebase Firestore
  - Firebase Storage
  - Picasso (for image loading)
  - Android Navigation Component
  - Material Design Components
- **Build System**: Gradle

---

## Installation and Setup

### Prerequisites
1. Ensure you have Android Studio installed.
2. Clone this repository:
   ```bash
   git clone https://github.com/your-repo/photoshare.git
   ```

### Steps
1. Open the project in Android Studio.
2. Sync the Gradle files.
3. Ensure you have added the `google-services.json` file to the `app/` directory for Firebase integration.
4. Run the application on an Android emulator or a physical device.

---

## Build Configuration

Below is the `build.gradle` configuration for the app module, detailing the plugins, dependencies, and build settings used for this project:

```gradle
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.burak.photoshare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.burak.photoshare"
        minSdk = 24
        targetSdk = 34
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")

    implementation("com.squareup.picasso:picasso:2.8")

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
}
```

---

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature.
3. Commit your changes and push the branch.
4. Submit a pull request.

---
