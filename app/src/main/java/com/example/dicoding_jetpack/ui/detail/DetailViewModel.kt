package com.example.dicoding_jetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.vo.Resource

class DetailViewModel(private val catalogRepository: CatalogRepository)  : ViewModel() {

    val id = MutableLiveData<String>()
    var type : Boolean = true

    fun setSelectedId(id: String,type:Boolean) {
        this.id.value = id
        this.type=type
    }
    var movieDetail: LiveData<Resource<LocalData>> = Transformations.switchMap(id) { mCourseId ->
        catalogRepository.getCourseWithMovie(mCourseId)
    }

    var tvDetail: LiveData<Resource<LocalData>> = Transformations.switchMap(id) { mCourseId ->
        catalogRepository.getCourseWithTv(mCourseId)
    }

    fun setBookmarkMovie() {
        val moduleResource = movieDetail.value
        if (moduleResource != null) {
            val newState = !moduleResource.data?.bookmarked!!
            catalogRepository.setBookmarkMovie(moduleResource.data, newState)

        }
    }

    fun setBookmarkTv() {
        val moduleResource = tvDetail.value
        if (moduleResource != null) {
            val newState = !moduleResource.data?.bookmarked!!
            catalogRepository.setBookmarkTv(moduleResource.data, newState)

        }
    }

//    fun getTvShow(): LiveData<Resource<LocalData>> = catalogRepository.getCourseWithTv(id)
//
//
//    fun getMovie(): LiveData<Resource<LocalData>> = catalogRepository.getCourseWithMovie(id)

}