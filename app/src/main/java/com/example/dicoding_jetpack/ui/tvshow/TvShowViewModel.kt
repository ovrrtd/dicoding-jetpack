package com.example.dicoding_jetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.vo.Resource

class TvShowViewModel (private val catalogRepository: CatalogRepository) : ViewModel(){

    fun getTvShow(): LiveData<Resource<List<LocalData>>> = catalogRepository.getAllTvShows()
}