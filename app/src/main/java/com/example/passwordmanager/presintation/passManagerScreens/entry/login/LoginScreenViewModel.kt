package com.example.passwordmanager.presintation.passManagerScreens.entry.login

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.navigation.Screens
import com.example.passwordmanager.presintation.passManagerScreens.entry.AuthScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val sharedPreferences: SharedPreferences)  :ViewModel() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private var _state by mutableStateOf(
        AuthScreenState(
            userName = "",
            isErrorName  = false,
            userNameErrorMessage = "",
            password = "",
            isErrorPassword = false,
            passwordErrorMessage = "",
            showPassword = false,
        )
    )

    val state: State<AuthScreenState>
        get() = derivedStateOf { _state }

    fun onPasswordChange(password:String){
        _state = _state.copy(
            password = password,
            isErrorPassword = false,
            passwordErrorMessage = ""
        )

    }


    fun onIconButtonClick(){
        val newShowPassword = _state.showPassword.not()
        _state = _state.copy(
            showPassword = newShowPassword
        )
    }

    fun onLoginButtonClick(navController: NavHostController) {
        if (_state.password.isEmpty()){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = "Please enter your password"
            )

        }else{
            val sharedPreferencesPassword = sharedPreferences.getString("password","")

            if(sharedPreferencesPassword != null){
                if (sharedPreferencesPassword == _state.password){
                    // navigate to home screen
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Login.route) {
                            inclusive = true
                        }
                    }
                }else{
                    _state=_state.copy(
                        isErrorPassword = true,
                        passwordErrorMessage = "Please enter valid password"
                    )
                }
            }
        }



    }
    fun onFingerPrintClick(context: Context, navController: NavHostController){
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(context as FragmentActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Login.route){
                            inclusive = true

                        }
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

         promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication")
            .setSubtitle("Please use fingerprint to verify identity")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}