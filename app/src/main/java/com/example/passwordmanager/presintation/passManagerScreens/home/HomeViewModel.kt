package com.example.passwordmanager.presintation.passManagerScreens.home

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.passwordmanager.data.local.Account
import com.example.passwordmanager.domain.repositorys.AccountRepository
import com.example.passwordmanager.presintation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:AccountRepository):ViewModel() {
    private  val _accounts: MutableStateFlow<List<Account>> = MutableStateFlow(emptyList())
    val accounts = _accounts.asStateFlow()
    init {
        getAllAccounts()
    }

    private fun getAllAccounts(){

        viewModelScope.launch (Dispatchers.IO){
            repo.getAllAccounts().collect{
                _accounts.value = it.toMutableList()
            }
        }
    }

    fun onCardClick(accountId: Int, navController: NavHostController){
        navController.navigate("${Screens.UpdateAccount.route}/$accountId")
    }

    fun onCopyIconClick(clipboardManager: ClipboardManager,account:Account) {
        clipboardManager.setText(
            buildAnnotatedString {

                append(account.email)
                append("\n")
                append(account.password)
            }
        )
    }

}