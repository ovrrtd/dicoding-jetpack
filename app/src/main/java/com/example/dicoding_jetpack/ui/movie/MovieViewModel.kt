package com.example.dicoding_jetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.vo.Resource

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel(){

    fun getMovies(): LiveData<Resource<List<LocalData>>> =
        catalogRepository.getAllMovies()
}