package com.example.passwordmanager.presintation.navigation

sealed class Screens(val route:String){
    object Splash:Screens(
        route = "splash"
    )
    object Intro:Screens(
        route = "intro"
    )
    object SignUp:Screens(
        route = "signUp"
    )
    object Login:Screens(
        route = "login"
    )
    object Home:Screens(
        route = "home"
    )
    object NewAccount:Screens(
        route = "newAccount"
    )
    object UpdateAccount:Screens(
        route = "updateAccount"
    )
}