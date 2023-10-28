package com.example.passwordmanager.presintation.passManagerScreens.newAccount

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.passwordmanager.data.local.Account
import com.example.passwordmanager.domain.repositorys.AccountRepository
import com.example.passwordmanager.presintation.passManagerScreens.AccountScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewAccountScreenViewModel @Inject constructor(private val repo:AccountRepository) :ViewModel() {
    private var _state by mutableStateOf(AccountScreenState())


    val state: State<AccountScreenState>
        get() =  derivedStateOf{_state}

    fun resetState(){
        _state =  _state.copy(
            userName = "",
            isUserNameError = false,
            userNameErrorMessage = "",
            email = "",
            isEmailError = false,
            emailErrorMessage = "",
            password = "",
            isPasswordError = false,
            passwordErrorMessage = ""
        )
    }

    fun onUserNameChange(userName: String){
        _state =  _state.copy(
            userName = userName,
            isUserNameError = false,
            userNameErrorMessage = ""
        )
    }


    fun onEmailChange(email: String){
        _state =  _state.copy(
            email = email,
            isEmailError = false,
            emailErrorMessage = ""
        )
    }

    fun onPasswordChange(password: String){
        _state =  _state.copy(
            password = password,
            isPasswordError = false,
            passwordErrorMessage = ""
        )
    }

    fun onShowPasswordClick(){
        _state =  _state.copy(
            showPassword = _state.showPassword.not()
        )
    }

    fun onSaveClick(navController: NavHostController) {
        if (_state.userName!="" &&_state.email!="" &&_state.password!=""){
            viewModelScope.launch (Dispatchers.IO){
                repo.addAccount(
                    Account(
                        userName = _state.userName,
                        email = _state.email,
                        password = _state.password
                    )
                )
                withContext(Dispatchers.Main){
                    navController.popBackStack()
                }

            }
        }

        if (_state.userName == ""){
            _state = _state.copy(
                isUserNameError = true,
                userNameErrorMessage = "Please enter Account userName"
            )
        }
        if (_state.email == ""){
            _state = _state.copy(
               isEmailError = true,
                emailErrorMessage = "Please enter Account email"
            )
        }
        if (_state.password == ""){
            _state = _state.copy(
                isPasswordError = true,
                passwordErrorMessage = "Please enter Account password"
            )
        }



    }


}