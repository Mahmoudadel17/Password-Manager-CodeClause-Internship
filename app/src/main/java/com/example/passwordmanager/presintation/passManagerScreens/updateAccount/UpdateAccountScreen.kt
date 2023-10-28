package com.example.passwordmanager.presintation.passManagerScreens.updateAccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.passwordmanager.presintation.components.ButtonClickOn
import com.example.passwordmanager.presintation.components.EmailEditText
import com.example.passwordmanager.presintation.components.PasswordEditText
import com.example.passwordmanager.presintation.components.UserNameEditText


@Composable
fun UpdateAccountScreen(accountId:Int,updateAccountScreenViewModel: UpdateAccountScreenViewModel,navController:NavHostController) {
    LaunchedEffect(Unit){
        updateAccountScreenViewModel.setAccountId(accountId)
    }
    val state = updateAccountScreenViewModel.state.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = "Update Account",modifier = Modifier.padding(bottom = 100.dp), fontSize = 20.sp,color = MaterialTheme.colorScheme.secondary)

        UserNameEditText(
            userName = state.userName,
            isUsernameError = state.isUserNameError,
            userNameErrorMessage = state.userNameErrorMessage,
            onValueChange = { updateAccountScreenViewModel.onUserNameChange(it) }
        )
        EmailEditText(
            email = state.email,
            isErrorEmail = state.isEmailError,
            emailErrorMessage = state.emailErrorMessage,
            onValueChange = {updateAccountScreenViewModel.onEmailChange(it)}
        )

        PasswordEditText(
            password = state.password,
            isErrorPassword = state.isPasswordError,
            passwordErrorMessage = state.passwordErrorMessage,
            showPassword = state.showPassword,
            onValueChange = {updateAccountScreenViewModel.onPasswordChange(it) }
        ) {
            updateAccountScreenViewModel.onShowPasswordClick()
        }

        Row {
            ButtonClickOn(
                buttonText = "Delete",
                paddingValue = 150,
                Modifier.weight(0.50f)
            ) {
                updateAccountScreenViewModel.onDeleteAccount()
            }
            Spacer(modifier = Modifier.width(5.dp))
            ButtonClickOn(
                buttonText = "Save",
                paddingValue = 150,
                Modifier.weight(0.50f)
            ) {
                updateAccountScreenViewModel.onSaveClick(navController)
            }
        }

        DeleteConfirmationDialog(
            showDialog = state.showDialog,
            onConfirm =  {updateAccountScreenViewModel.onDeleteConfirm(navController)}){
            updateAccountScreenViewModel.onDeleteDialogCancel()
        }
    }

}



@Composable
fun DeleteConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = onDismiss,
            title = { Text("Confirm Deletion", color =  MaterialTheme.colorScheme.tertiary)},
            text = { Text("Are you sure you want to delete this Alarm?", color = MaterialTheme.colorScheme.tertiary) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor =MaterialTheme.colorScheme.secondary),
                ) {
                    Text("Delete", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.background)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor =MaterialTheme.colorScheme.secondary),
                ) {
                    Text("Cancel",color = MaterialTheme.colorScheme.background)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}