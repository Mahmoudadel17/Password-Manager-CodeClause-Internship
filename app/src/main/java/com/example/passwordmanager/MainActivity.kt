package com.example.passwordmanager

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.example.passwordmanager.di.App
import com.example.passwordmanager.presintation.navigation.AppNavigate
import com.example.passwordmanager.presintation.passManagerScreens.entry.AuthViewModel
import com.example.passwordmanager.presintation.passManagerScreens.entry.login.LoginScreenViewModel
import com.example.passwordmanager.presintation.passManagerScreens.entry.signUp.SignUpViewModel
import com.example.passwordmanager.presintation.passManagerScreens.home.HomeViewModel
import com.example.passwordmanager.presintation.passManagerScreens.newAccount.NewAccountScreenViewModel
import com.example.passwordmanager.presintation.passManagerScreens.updateAccount.UpdateAccountScreenViewModel
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val loginScreenViewModel by viewModels<LoginScreenViewModel>()
    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    private val newAccountViewModel by viewModels<NewAccountScreenViewModel>()
    private val updateAccountViewModel by viewModels<UpdateAccountScreenViewModel>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkBiometricsAuthentication()
        setContent {
            PasswordManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate(
                        authViewModel,
                        loginScreenViewModel,
                        signUpViewModel,
                        homeViewModel,
                        newAccountViewModel,
                        updateAccountViewModel
                    )
                }
            }
        }
    }

    private fun checkBiometricsAuthentication() {
        val myApp = application as App
        // Obtain a BiometricManager instance using a context
        val biometricManager = BiometricManager.from(this)

        // Check the availability and status of biometric authentication

        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                myApp.isAuthenticateUsingBiometricsAvailable = true
            }
        }
    }
}

