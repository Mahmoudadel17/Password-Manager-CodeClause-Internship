package com.example.passwordmanager.presintation.passManagerScreens.home


import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.components.AccountCard
import com.example.passwordmanager.presintation.navigation.Screens

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
    val clipboardManager = LocalClipboardManager.current
    val accounts by homeViewModel.accounts.collectAsState()

    var doubleBackToExitPressedOnce = false
    val activity = LocalOnBackPressedDispatcherOwner.current as ComponentActivity
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                onClick = {
                    // navigate to new Account screen
                   navController.navigate(Screens.NewAccount.route)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = MaterialTheme.colorScheme.background,
                    contentDescription = "Add",
                )
            }
        }
    ){
        Box(modifier = Modifier.padding(it)){
            LazyColumn{
                item {
                    Text(text = "  Manage," , fontSize = 25.sp, color = MaterialTheme.colorScheme.secondary)
                    Text(text = "    Your Password",  fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary)
                }
                items(accounts){account->
                    AccountCard(account = account,{homeViewModel.onCardClick(account.id,navController)}){
                        // view model on icon click action
                        homeViewModel.onCopyIconClick(clipboardManager,account)
                        Toast.makeText(context,"Account Copied",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if(accounts.isEmpty()){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text ="No Accounts added yet.",
                        style = MaterialTheme.typography.headlineSmall.merge(
                            TextStyle(
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        ),
                    )
                }
            }
        }
        //Back Handler
        BackHandler(onBack = {
            if (doubleBackToExitPressedOnce) {
                finishAffinity(activity)
            } else {
                doubleBackToExitPressedOnce = true
                Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        })






    }

}