package com.example.dicoding_jetpack.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicoding_jetpack.data.source.remote.response.ContentResponse
import com.example.dicoding_jetpack.utils.EspressoIdlingResource
import com.example.dicoding_jetpack.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }
//
//    fun getAllMovies(): List<ContentResponse> = jsonHelper.loadMovie()
//
//    fun getAllTvShows(): List<ContentResponse> = jsonHelper.loadTv()

    fun getAllMovies(): LiveData<ApiResponse<List<ContentResponse>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ContentResponse>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadMovie())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getMoviebyId(courseId : String):LiveData<ApiResponse<ContentResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed({
            val movies=jsonHelper.loadMovie()
            for (movie in movies){
                if (movie.id==courseId){
                    result.value = ApiResponse.success(movie)
                }
            }

            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getTvbyId(courseId : String):LiveData<ApiResponse<ContentResponse>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed({
            val tvs=jsonHelper.loadTv()
            for (tv in tvs){
                if (tv.id==courseId){
                    result.value = ApiResponse.success(tv)
                }
            }

            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }

    fun getAllTvShows() : LiveData<ApiResponse<List<ContentResponse>>>  {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ContentResponse>>>()
        handler.postDelayed(
            {
                result.value = ApiResponse.success(jsonHelper.loadTv())
                EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
        )
        return result
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(courseResponses: List<ContentResponse>)
    }


    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(courseResponses: List<ContentResponse>)
    }

}