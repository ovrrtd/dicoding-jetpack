package com.example.dicoding_jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicoding_jetpack.data.source.local.LocalDataSource
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.data.source.remote.ApiResponse
import com.example.dicoding_jetpack.data.source.remote.RemoteDataSource
import com.example.dicoding_jetpack.data.source.remote.response.ContentResponse
import com.example.dicoding_jetpack.utils.AppExecutors
import com.example.dicoding_jetpack.vo.Resource

class FakeCatalogRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val url: String = "https://image.tmdb.org/t/p/w185"
) :

    CatalogDataSource {


    override fun getAllMovies(): LiveData<Resource<List<LocalData>>> {
        return object :
            NetworkBoundResource<List<LocalData>, List<ContentResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<LocalData>> =
                localDataSource.getAllLocalMovie()

            override fun shouldFetch(data: List<LocalData>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(courseResponses: List<ContentResponse>) {
                val courseList = ArrayList<LocalData>()
                for (response in courseResponses) {
                    val course = LocalData(
                        response.id,
                        response.title,
                        response.description,
                        response.voteAverage,
                        true,
                        url + response.poster,
                        response.releaseDate
                    )
                    courseList.add(course)
                }

                localDataSource.insertLocal(courseList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<LocalData>>> {
        return object :
            NetworkBoundResource<List<LocalData>, List<ContentResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<LocalData>> =
                localDataSource.getAllLocalTv()

            override fun shouldFetch(data: List<LocalData>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllTvShows()

            public override fun saveCallResult(courseResponses: List<ContentResponse>) {
                val courseList = ArrayList<LocalData>()
                for (response in courseResponses) {
                    val course = LocalData(
                        response.id,
                        response.title,
                        response.description,
                        response.voteAverage,
                        false,
                        url + response.poster,
                        response.releaseDate
                    )
                    courseList.add(course)
                }

                localDataSource.insertLocal(courseList)
            }
        }.asLiveData()

    }

    override fun getCourseWithMovie(courseId: String): LiveData<Resource<LocalData>> {
        return object : NetworkBoundResource<LocalData, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<LocalData> =
                localDataSource.getLocalDatabyId(courseId)

            override fun shouldFetch(courseWithModule: LocalData?): Boolean =
                courseWithModule == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteDataSource.getMoviebyId(courseId)

            override fun saveCallResult(moduleResponses: ContentResponse) {
            }
        }.asLiveData()
    }

    override fun getCourseWithTv(courseId: String): LiveData<Resource<LocalData>> {
        return object : NetworkBoundResource<LocalData, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<LocalData> =
                localDataSource.getLocalDatabyId(courseId)

            override fun shouldFetch(courseWithModule: LocalData?): Boolean =
                courseWithModule == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteDataSource.getTvbyId(courseId)

            override fun saveCallResult(moduleResponses: ContentResponse) {
            }
        }.asLiveData()

//    override fun getCourseWithTv(courseId: String): LiveData<TvShowData> {
//        val courseResult = MutableLiveData<TvShowData>()
//        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
//            override fun onAllTvShowsReceived(courseResponses: List<ContentResponse>) {
//                lateinit var course: TvShowData
//                for (response in courseResponses) {
//                    if (response.id == courseId) {
//                        course = TvShowData(
//                            response.id,
//                            response.title,
//                            response.description,
//                            response.voteAverage,
//                            false,
//                            url + response.poster,
//                            response.releaseDate
//                        )
//                    }
//                }
//                courseResult.postValue(course)
//            }
//        })
//
//        return courseResult
//    }


    }

    override fun setBookmarkMovie(data :LocalData, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setCourseBookmark(data, state) }

    override fun setBookmarkTv(data :LocalData, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setCourseBookmark(data, state) }

    override fun getBookmarkedMovie(): LiveData<PagedList<LocalData>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedMovie(), config).build()
    }

//        localDataSource.getBookmarkedMovie()

    override fun getBookmarkedTv(): LiveData<PagedList<LocalData>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedTv(), config).build()}
}