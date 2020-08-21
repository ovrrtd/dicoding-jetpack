package com.example.dicoding_jetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.data.source.local.room.CatalogDao

class LocalDataSource private constructor(private val mCatalogDao: CatalogDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogDao)
    }

    fun getAllLocalMovie(): LiveData<List<LocalData>> = mCatalogDao.getAllLocalMovie()

    fun getAllLocalTv(): LiveData<List<LocalData>> = mCatalogDao.getAllLocalTv()

    fun getLocalDatabyId(courseId: String): LiveData<LocalData> =
        mCatalogDao.getLocalDatabyId(courseId)

    fun getBookmarkedMovie(): DataSource.Factory<Int, LocalData> = mCatalogDao.getBookmarkedMovie()
    fun getBookmarkedTv(): DataSource.Factory<Int, LocalData> = mCatalogDao.getBookmarkedTv()

    fun insertLocal(courses: ArrayList<LocalData>) = mCatalogDao.insertLocal(courses)

    fun deleteLocal(course: LocalData) = mCatalogDao.deleteLocal(course)

    fun setCourseBookmark(data: LocalData, newState: Boolean) {
        data.bookmarked = newState
        mCatalogDao.updateLocal(data)
    }

}