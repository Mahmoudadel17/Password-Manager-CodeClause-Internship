package com.example.passwordmanager.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.passwordmanager.data.local.Database
import com.example.passwordmanager.data.repositorys.AccountRepositoryImpl
import com.example.passwordmanager.domain.repositorys.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java,"AccountsDatabase").build()

    @Provides
    @Singleton
    fun providesDatabaseDao(dataBase: Database)=dataBase.getAccountDao()


    @Provides
    @Singleton
    fun providesAlarmScreenRepository(@ApplicationContext context: Context):AccountRepository =
        AccountRepositoryImpl(providesDatabaseDao(providesDatabase(context)))

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)



}