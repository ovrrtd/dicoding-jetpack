package com.example.dicoding_jetpack.di

import android.content.Context
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.LocalDataSource
import com.example.dicoding_jetpack.data.source.local.room.CatalogDatabase
import com.example.dicoding_jetpack.data.source.remote.RemoteDataSource
import com.example.dicoding_jetpack.utils.AppExecutors
import com.example.dicoding_jetpack.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CatalogRepository {
        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()
        return CatalogRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }
}