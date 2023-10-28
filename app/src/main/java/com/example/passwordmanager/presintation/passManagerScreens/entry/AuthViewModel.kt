package com.example.passwordmanager.presintation.passManagerScreens.entry

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val sharedPreferences:SharedPreferences) : ViewModel() {



    private fun checkSignUpComplete():Boolean{
        return sharedPreferences.getBoolean("SignUpCompleted",false)
    }

    fun determineNextScreen(navController: NavHostController) {
        val check = checkSignUpComplete()
        if (check){
            navController.navigate(Screens.Login.route){
                popUpTo(Screens.Splash.route) {
                    inclusive = true
                }
            }

        }else{
            navController.navigate(Screens.Intro.route){
                popUpTo(Screens.Splash.route) {
                    inclusive = true
                }
            }

        }
    }


}