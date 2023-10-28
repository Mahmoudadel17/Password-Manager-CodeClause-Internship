package com.example.passwordmanager.domain.repositorys

import com.example.passwordmanager.data.local.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun getAllAccounts():Flow<List<Account>>

    suspend fun addAccount(account: Account)

    suspend fun getAccount(accountId: Int):Account

    suspend fun deleteAccount(account: Account)

    suspend fun deleteAccountById(accountId: Int)
}