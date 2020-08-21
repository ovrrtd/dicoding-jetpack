package com.example.dicoding_jetpack.ui.favorit.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData

class FavMovieViewModel (private val catalogRepository: CatalogRepository) : ViewModel(){

    fun getMovies(): LiveData<PagedList<LocalData>> =
        catalogRepository.getBookmarkedMovie()
}