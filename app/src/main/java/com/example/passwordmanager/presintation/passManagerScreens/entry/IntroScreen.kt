package com.example.passwordmanager.presintation.passManagerScreens.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwordmanager.R
import com.example.passwordmanager.presintation.navigation.Screens

@Composable
fun IntroScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Add image here as a background
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Add other content on top of the image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            // Add your text or other UI elements here
            MyText("Monitoring")
            MyText("Password From")
            MyText("Anywhere")
            Text(
                text = "You Can Manage Your Passwords Anywhere, Anytime.",
                style = MaterialTheme.typography.bodyLarge.merge(
                    TextStyle(
                        color = Color.White
                    )
                ),
                modifier = Modifier.padding(top = 10.dp, bottom = 50.dp),
            )
            Button (colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                enabled = true,
                onClick = {
                    navController.navigate(Screens.SignUp.route){
                        popUpTo(Screens.Intro.route) {
                            inclusive = true
                        }
                    }
                          },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
            ){
                Text(text = "Get Started", fontSize = 30.sp, style = TextStyle(color =  Color.White,))
            }

        }
    }
}

@Composable
fun MyText(text:String) {
    Text(text = text,style = MaterialTheme.typography.displaySmall.merge(
        TextStyle(
            color = Color.White
        )
    ))
}