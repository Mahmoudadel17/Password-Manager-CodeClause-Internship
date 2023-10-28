package com.example.passwordmanager.presintation.passManagerScreens.entry.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwordmanager.R
import com.example.passwordmanager.di.App
import com.example.passwordmanager.presintation.components.ButtonClickOn
import com.example.passwordmanager.presintation.components.LottieAnimationShow
import com.example.passwordmanager.presintation.components.PasswordEditText


@Composable
fun LoginScreen(viewModel: LoginScreenViewModel, navController: NavHostController) {
    val state = viewModel.state
    val context = LocalContext.current
    val app = context.applicationContext as App
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {

        LottieAnimationShow(R.raw.login,250,10,0)

        PasswordEditText(
            password = state.value.password,
            isErrorPassword = state.value.isErrorPassword,
            passwordErrorMessage = state.value.passwordErrorMessage,
            showPassword = state.value.showPassword,
            onValueChange = {viewModel.onPasswordChange(it)}
        ) {
            viewModel.onIconButtonClick()
        }

        ButtonClickOn(buttonText = "Login", paddingValue = 220) {
            viewModel.onLoginButtonClick(navController)
        }
       if (app.isAuthenticateUsingBiometricsAvailable){
           Row{
               Text(
                   text ="Login using Finger Print!",
                   textDecoration = TextDecoration.Underline,
                   modifier = Modifier
                       .padding(top = 3.dp)
                       .clickable {
                           // finger print request
                           viewModel.onFingerPrintClick(context, navController)
                       },
                   color = MaterialTheme.colorScheme.secondary,
                   fontSize = 16.sp,
               )

           }
       }

    }
}