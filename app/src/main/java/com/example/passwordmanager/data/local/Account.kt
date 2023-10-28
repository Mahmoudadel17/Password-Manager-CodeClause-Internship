package com.example.passwordmanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val userName:String,
    val email:String,
    val password:String
)
