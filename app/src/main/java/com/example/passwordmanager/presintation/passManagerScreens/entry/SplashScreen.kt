package com.example.passwordmanager.presintation.passManagerScreens.entry

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwordmanager.R
import com.example.passwordmanager.presintation.components.LottieAnimationShow
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
   ){
        Text(
            text = "PassWo",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = "Manage Your Passwords",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 0.2.em,
        )
        LottieAnimationShow(R.raw.loading,200,250,0)
   }
    LaunchedEffect(Unit ){
        delay(2000)
        authViewModel.determineNextScreen(navController)
    }
}
