package com.example.passwordmanager.presintation.passManagerScreens.entry.signUp

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.navigation.Screens
import com.example.passwordmanager.presintation.passManagerScreens.entry.AuthScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val sharedPreferences: SharedPreferences)  : ViewModel() {

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



    fun onUserNameChange(userName:String){
        _state = _state.copy(
            userName = userName,
            isErrorName = false,
            userNameErrorMessage = ""
        )
    }

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
        var confirm = true
        if (_state.userName.isEmpty()){
            _state=_state.copy(
                isErrorName = true,
                userNameErrorMessage = "Please enter your name"
            )
            confirm = false
        }
        if (_state.password.isEmpty()){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = "Please enter your password"
            )
            confirm = false

        }
        if (_state.password.length < 8){
            _state=_state.copy(
                isErrorPassword = true,
                passwordErrorMessage = "Please enter valid password (>8 char)"
            )
            confirm = false

        }
        if (confirm){
            // make sign up complete in sheared Preferences
            val editor = sharedPreferences.edit()
            editor.putBoolean("SignUpCompleted",true)
            editor.apply()

            editor.putString ("password",_state.password)
            editor.apply()
            // add user name to  sheared Preferences
            editor.putString ("userName",_state.userName)
            editor.apply()
            // navigate to home screen
            navController.navigate(Screens.Home.route){
                popUpTo(Screens.SignUp.route) {
                    inclusive = true
                }
            }
        }

    }
}