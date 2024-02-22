package com.example.task_5_flavors__crashlytics

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
class BaseApplication : Application() {
    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig
    private lateinit var configSettings: FirebaseRemoteConfigSettings

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this)
        setupFirebaseRemoteConfig()
    }
    private fun setupFirebaseRemoteConfig() {
        Log.d("TAG", "setupFirebaseRemoteConfig: initilized")
        firebaseRemoteConfig = Firebase.remoteConfig
        configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }
    fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return firebaseRemoteConfig
    }
}
