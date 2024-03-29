plugins {
    id("com.android.application")
}

android {
    namespace = "com.vaxwe.mascotasapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vaxwe.mascotasapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.android.volley:volley:1.2.1") /* MYSQL */
    implementation("com.airbnb.android:lottie:3.0.1") /* ANIMACIONES LOTTIE*/
    implementation("com.squareup.picasso:picasso:2.71828") /* GESTIONAR IMAGENES*/
    implementation("androidx.recyclerview:recyclerview:1.2.1") /* RECYCLERVIEW*/
    implementation("androidx.cardview:cardview:1.0.0") /* CARDVIEW*/
    implementation("de.hdodenhof:circleimageview:3.0.1") /* IMAGEN CIRCULAR*/
    implementation("com.getbase:floatingactionbutton:1.10.1")
    implementation("com.orhanobut:dialogplus:1.11@aar") /* VISTA PLUS*/


}