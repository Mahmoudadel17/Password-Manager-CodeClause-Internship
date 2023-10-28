package com.example.passwordmanager.presintation.passManagerScreens.entry.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.passwordmanager.R
import com.example.passwordmanager.presintation.components.ButtonClickOn
import com.example.passwordmanager.presintation.components.LottieAnimationShow
import com.example.passwordmanager.presintation.components.PasswordEditText
import com.example.passwordmanager.presintation.components.UserNameEditText


@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavHostController) {
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        LottieAnimationShow(R.raw.signup,200,10,50)
        UserNameEditText(
            userName = state.value.userName,
            isUsernameError  = state.value.isErrorName,
            userNameErrorMessage = state.value.userNameErrorMessage,
            onValueChange = {viewModel.onUserNameChange(it)}
        )
        PasswordEditText(
            password = state.value.password,
            isErrorPassword = state.value.isErrorPassword,
            passwordErrorMessage = state.value.passwordErrorMessage,
            showPassword = state.value.showPassword,
            onValueChange = {viewModel.onPasswordChange(it)}
        ) {
            viewModel.onIconButtonClick()
        }

        ButtonClickOn(buttonText = "Sign Up", paddingValue = 170) {
            viewModel.onLoginButtonClick(navController)
        }

    }
}
