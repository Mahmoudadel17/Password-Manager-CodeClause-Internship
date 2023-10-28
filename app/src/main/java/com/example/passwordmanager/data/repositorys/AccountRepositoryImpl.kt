package com.example.passwordmanager.data.repositorys

import com.example.passwordmanager.data.local.Account
import com.example.passwordmanager.data.local.AccountDao
import com.example.passwordmanager.domain.repositorys.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl  @Inject constructor(private val dao:AccountDao) :AccountRepository {
    override suspend fun getAllAccounts(): Flow<List<Account>> {
        return dao.getAllAccounts()
    }

    override suspend fun addAccount(account: Account) {
        dao.addAccount(account)
    }

    override suspend fun getAccount(accountId: Int): Account {
        return dao.getAccount(accountId)
    }

    override suspend fun deleteAccount(account: Account) {
        dao.deleteAccount(account)
    }

    override suspend fun deleteAccountById(accountId: Int) {
        dao.deleteAccountById(accountId)
    }
}