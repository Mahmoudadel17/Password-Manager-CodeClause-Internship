package com.example.passwordmanager.presintation.passManagerScreens.updateAccount

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
class UpdateAccountScreenViewModel @Inject constructor(private val repo:AccountRepository) :ViewModel() {

    private var _state by mutableStateOf(AccountScreenState())


    val state: State<AccountScreenState>
        get() =  derivedStateOf{_state}

    private var checkId = false
    private var accountID = 0

    fun setAccountId(accountId:Int){
        checkId = true
        viewModelScope.launch(Dispatchers.IO) {
            val account = repo.getAccount(accountId)
            accountID = account.id
            _state = _state.copy(
                userName = account.userName,
                email = account.email,
                password = account.password
            )
        }

    }

    fun onUserNameChange(userName: String){
        _state =  _state.copy(
            userName = userName,
            isUserNameError = false,
            userNameErrorMessage = ""
        )
    }

    fun onDeleteAccount(){
        _state = _state.copy(
            showDialog = true
        )
    }

    fun onDeleteDialogCancel(){
        _state = _state.copy(
            showDialog = false
        )
    }

    fun onDeleteConfirm(navController: NavHostController){
        viewModelScope.launch(Dispatchers.IO) {
           if (checkId){
               repo.deleteAccountById(accountID)
               withContext(Dispatchers.Main){
                   navController.popBackStack()
               }
           }
        }
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
        if (checkId){
            viewModelScope.launch (Dispatchers.IO){
                repo.addAccount(Account(accountID,_state.userName,_state.email,_state.password))
                withContext(Dispatchers.Main){
                    navController.popBackStack()
                }
            }
        }
    }
}