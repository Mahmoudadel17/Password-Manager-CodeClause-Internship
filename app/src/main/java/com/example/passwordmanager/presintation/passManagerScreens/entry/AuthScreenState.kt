package com.example.passwordmanager.presintation.passManagerScreens.entry

data class AuthScreenState(
    val userName:String,
    val isErrorName:Boolean,
    val userNameErrorMessage:String,

    val password:String,
    val isErrorPassword:Boolean,
    val passwordErrorMessage:String,

    val showPassword:Boolean,
)