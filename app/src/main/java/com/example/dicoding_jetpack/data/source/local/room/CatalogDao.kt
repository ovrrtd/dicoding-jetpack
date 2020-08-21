package com.example.dicoding_jetpack.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.dicoding_jetpack.data.source.local.entity.LocalData

@Dao
interface CatalogDao {
    @WorkerThread
    @Query("SELECT * FROM localentities WHERE type =1")
    fun getAllLocalMovie(): LiveData<List<LocalData>>
    @WorkerThread
    @Query("SELECT * FROM localentities WHERE type = 0")
    fun getAllLocalTv(): LiveData<List<LocalData>>
    @WorkerThread
    @Query("SELECT * FROM localentities WHERE id = :courseId")
    fun getLocalDatabyId(courseId: String): LiveData<LocalData>
    @WorkerThread
    @Query("SELECT * FROM localentities where bookmarked = 1 and type = 0")
    fun getBookmarkedTv(): DataSource.Factory<Int, LocalData>
    @WorkerThread
    @Query("SELECT * FROM localentities where bookmarked = 1 and type = 1")
    fun getBookmarkedMovie(): DataSource.Factory<Int, LocalData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocal(courses: List<LocalData>)

    @Update
    fun updateLocal(course: LocalData)

    @Delete
    fun deleteLocal(course : LocalData)

}