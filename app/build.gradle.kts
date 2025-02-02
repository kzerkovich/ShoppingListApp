plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.com.google.devtools.ksp)
	kotlin("kapt") version "2.1.0"
}

android {
	namespace = "com.kzerk.shoppinglistapp"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.kzerk.shoppinglistapp"
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
		dataBinding {
			enable = true
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)

	implementation (libs.kotlinx.coroutines.core)
	implementation (libs.kotlinx.coroutines.android)

	implementation(libs.androidx.room)
	ksp(libs.room.compiler)
	implementation (libs.lifecycle.viewmodel.ktx)
	implementation (libs.lifecycle.runtime.ktx)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	implementation(libs.android.dagger)
	kapt(libs.android.dagger.compiler)
}