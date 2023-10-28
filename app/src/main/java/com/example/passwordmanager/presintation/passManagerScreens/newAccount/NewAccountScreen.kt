package com.example.passwordmanager.presintation.passManagerScreens.newAccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.components.ButtonClickOn
import com.example.passwordmanager.presintation.components.EmailEditText
import com.example.passwordmanager.presintation.components.PasswordEditText
import com.example.passwordmanager.presintation.components.UserNameEditText

@Composable
fun NewAccountScreen(
    newAccountScreenViewModel: NewAccountScreenViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit){
        newAccountScreenViewModel.resetState()
    }
    val state = newAccountScreenViewModel.state.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = "Add new Account",modifier = Modifier.padding(bottom = 100.dp), fontSize = 20.sp,color = MaterialTheme.colorScheme.secondary)
        
        UserNameEditText(
            userName = state.userName,
            isUsernameError = state.isUserNameError,
            userNameErrorMessage = state.userNameErrorMessage,
            onValueChange = { newAccountScreenViewModel.onUserNameChange(it) }
        )
        EmailEditText(
            email = state.email,
            isErrorEmail = state.isEmailError,
            emailErrorMessage = state.emailErrorMessage,
            onValueChange = {newAccountScreenViewModel.onEmailChange(it)}
        )
        
        PasswordEditText(
            password = state.password,
            isErrorPassword = state.isPasswordError,
            passwordErrorMessage = state.passwordErrorMessage,
            showPassword = state.showPassword,
            onValueChange = {newAccountScreenViewModel.onPasswordChange(it) }
        ) {
            newAccountScreenViewModel.onShowPasswordClick()
        }


        ButtonClickOn(
            buttonText = "Save",
            paddingValue = 150
        ) {
            newAccountScreenViewModel.onSaveClick(navController)
        }
    }
}