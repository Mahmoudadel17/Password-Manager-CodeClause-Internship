package com.example.passwordmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun getAccountDao():AccountDao
}