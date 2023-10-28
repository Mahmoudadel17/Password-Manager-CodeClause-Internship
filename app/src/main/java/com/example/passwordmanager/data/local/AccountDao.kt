package com.example.passwordmanager.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AccountDao {

    @Query("SELECT * FROM Accounts")
    fun getAllAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM Accounts WHERE id= :id")
    fun getAccount(id:Int) : Account

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)


    @Query("Delete From Accounts WHERE id= :id")
    fun deleteAccountById(id:Int)

}