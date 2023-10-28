package com.example.passwordmanager.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {
    var isAuthenticateUsingBiometricsAvailable = false
}