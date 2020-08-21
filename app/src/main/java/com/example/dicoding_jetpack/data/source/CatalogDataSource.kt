package com.example.dicoding_jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.vo.Resource

interface CatalogDataSource {
    fun getAllMovies(): LiveData<Resource<List<LocalData>>>
    fun getAllTvShows(): LiveData<Resource<List<LocalData>>>
    fun getCourseWithMovie(courseId: String): LiveData<Resource<LocalData>>
    fun getCourseWithTv(courseId: String): LiveData<Resource<LocalData>>

    fun getBookmarkedMovie(): LiveData<PagedList<LocalData>>
    fun getBookmarkedTv(): LiveData<PagedList<LocalData>>
    fun setBookmarkMovie(data: LocalData, state: Boolean)

    fun setBookmarkTv(data: LocalData, state: Boolean)

}