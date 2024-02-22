package com.example.task_5_flavors__crashlytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.task_5_flavors__crashlytics.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializingBinding()
        titleBar()
        clickListeners()
        firebaseRemoteConfig = (application as BaseApplication).getFirebaseRemoteConfig()
        getValueFromFirebaseConfig()
    }

    private fun getValueFromFirebaseConfig() {
        Log.d("MainActivity", "getValueFromFirebaseConfig called")
        if (::firebaseRemoteConfig.isInitialized) {
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d("MainActivity", "Config params updated: $updated")
                        val text = firebaseRemoteConfig.getString(CommonKeys.FIREBASE_CONFIG_KEY)
                        Log.d("Hello", "Remote Config Value: $text")
                        binding.textViewRemoteConfig.text = text
                    } else {
                        Log.e("MainActivity", "Firebase Remote Config fetch failed")
                        Toast.makeText(
                            this,
                            getString(R.string.failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun titleBar() {
        val flavorName = getString(R.string.app_name)
        title = flavorName
    }
    private fun clickListeners() {
        binding.buttonCrash.setOnClickListener(View.OnClickListener {
            throw RuntimeException(getString(R.string.crash_test))
        })
    }
    private fun initializingBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

