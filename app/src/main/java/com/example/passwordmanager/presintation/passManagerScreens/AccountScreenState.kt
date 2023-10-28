package com.example.passwordmanager.presintation.passManagerScreens

data class AccountScreenState(
    var userName:String = "",
    var isUserNameError:Boolean = false,
    var userNameErrorMessage:String = "",
    var email:String = "",
    var isEmailError:Boolean = false,
    var emailErrorMessage:String = "",
    var password:String = "",
    var showPassword:Boolean = false,
    var isPasswordError:Boolean = false,
    var passwordErrorMessage:String = "",
    var showDialog:Boolean = false
)
