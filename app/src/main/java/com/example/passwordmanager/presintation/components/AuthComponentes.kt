package com.example.passwordmanager.presintation.components



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R


@Composable
fun TextFieldColors() : TextFieldColors{
    return  OutlinedTextFieldDefaults.colors(
        // text color
        focusedTextColor = MaterialTheme.colorScheme.secondary,
        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
        errorTextColor = MaterialTheme.colorScheme.secondary,
        // label color
        focusedLabelColor = MaterialTheme.colorScheme.secondary,
        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
        errorLabelColor = Color.Red,
        disabledLabelColor= MaterialTheme.colorScheme.secondary ,

        cursorColor = MaterialTheme.colorScheme.secondary,
        errorBorderColor = Color.Red,
        // icon color
        focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,


    )

}







@Composable
fun UserNameEditText(
    userName:String ,
    isUsernameError:Boolean ,
    userNameErrorMessage:String,
    onValueChange:(String) -> Unit) {
    OutlinedTextField(
        label = { Text("UserName") },
        value = userName,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldColors(),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        isError = isUsernameError,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "")
        }
    )
    Row {
        Text(
            userNameErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}




@Composable
fun EmailEditText(
    email:String ,
    isErrorEmail:Boolean ,
    emailErrorMessage:String,
    onValueChange:(String) -> Unit) {
    OutlinedTextField(
        label = { Text("Email") },
        value = email,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = {
            onValueChange(it)

        },
        colors = TextFieldColors(),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
        ,
        isError = isErrorEmail,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "email icon")
        }
    )
    Row {
        Text(
            emailErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}


@Composable
fun PasswordEditText(
    password:String ,
    isErrorPassword:Boolean ,
    passwordErrorMessage:String ,
    showPassword:Boolean,
    onValueChange:(String) -> Unit,
    onIconButtonClick:() -> Unit
) {
    OutlinedTextField(
        label = { Text("Password") },
        value = password,
        isError = isErrorPassword,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = {
            onValueChange(it)
        },
        colors =  TextFieldColors(),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {
                onIconButtonClick()
            }) {
                Icon(painter = painterResource(id = if(showPassword) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                    contentDescription = "password icon")
            }
        }
    )
    Row {
        Text(
            passwordErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }

}


@Composable
fun ButtonClickOn(
    buttonText:String,
    paddingValue:Int,
    modifier: Modifier = Modifier
    ,onButtonClick:() -> Unit ) {
    Button (colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = modifier
            .padding(top =paddingValue.dp)
            .fillMaxWidth(1f)
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )

    ){
        Text(text = buttonText, fontSize = 30.sp, style = TextStyle(color = MaterialTheme.colorScheme.secondary))
    }
}