package com.example.dicoding_jetpack.ui.favorit.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData

class FavTvShowViewModel (private val catalogRepository: CatalogRepository) : ViewModel(){
    fun getTvShow(): LiveData<PagedList<LocalData>> = catalogRepository.getBookmarkedTv()

}