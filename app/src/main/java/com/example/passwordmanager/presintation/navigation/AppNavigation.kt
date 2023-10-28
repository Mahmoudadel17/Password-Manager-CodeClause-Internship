package com.example.passwordmanager.presintation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.passwordmanager.presintation.passManagerScreens.entry.AuthViewModel
import com.example.passwordmanager.presintation.passManagerScreens.entry.IntroScreen
import com.example.passwordmanager.presintation.passManagerScreens.entry.login.LoginScreen
import com.example.passwordmanager.presintation.passManagerScreens.entry.signUp.SignUpScreen
import com.example.passwordmanager.presintation.passManagerScreens.entry.SplashScreen
import com.example.passwordmanager.presintation.passManagerScreens.entry.login.LoginScreenViewModel
import com.example.passwordmanager.presintation.passManagerScreens.entry.signUp.SignUpViewModel
import com.example.passwordmanager.presintation.passManagerScreens.home.HomeScreen
import com.example.passwordmanager.presintation.passManagerScreens.home.HomeViewModel
import com.example.passwordmanager.presintation.passManagerScreens.newAccount.NewAccountScreen
import com.example.passwordmanager.presintation.passManagerScreens.newAccount.NewAccountScreenViewModel
import com.example.passwordmanager.presintation.passManagerScreens.updateAccount.UpdateAccountScreen
import com.example.passwordmanager.presintation.passManagerScreens.updateAccount.UpdateAccountScreenViewModel


@Composable
fun AppNavigate(
    authViewModel: AuthViewModel,
    loginScreenViewModel: LoginScreenViewModel,
    signUpViewModel: SignUpViewModel,
    homeViewModel: HomeViewModel,
    newAccountViewModel: NewAccountScreenViewModel,
    updateAccountViewModel: UpdateAccountScreenViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Splash.route){
        composable(route = Screens.Splash.route){
            SplashScreen(navController,authViewModel)
        }
        composable(route = Screens.Intro.route){
            IntroScreen(navController)
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(signUpViewModel,navController)
        }
        composable(route = Screens.Login.route){
            LoginScreen(loginScreenViewModel,navController)
        }
        composable(route = Screens.Home.route){
            HomeScreen(homeViewModel,navController)
        }
        composable(route = Screens.NewAccount.route){
            NewAccountScreen(newAccountViewModel,navController)
        }
        composable(route = "${Screens.UpdateAccount.route}/{accountId}", arguments = listOf(
            navArgument("accountId"){type= NavType.IntType}
        )){
            val id = it.arguments?.getInt("accountId")
            id?.let {
                UpdateAccountScreen(id, updateAccountViewModel ,navController)
            }
        }
    }
}















