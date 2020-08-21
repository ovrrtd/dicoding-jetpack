package com.example.dicoding_jetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.dicoding_jetpack.data.source.local.LocalDataSource
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.data.source.remote.RemoteDataSource
import com.example.dicoding_jetpack.utils.AppExecutors
import com.example.dicoding_jetpack.utils.DataDummy
import com.example.dicoding_jetpack.utils.LiveDataTestUtil
import com.example.dicoding_jetpack.utils.PagedListUtil
import com.example.dicoding_jetpack.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CatalogRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val academyRepository = FakeCatalogRepository(remote,local,appExecutors)

    private val courseResponsesMovie = DataDummy.generateFakeMovie()
    private val courseIdMovie = courseResponsesMovie[0].id
    private val courseResponsesTv = DataDummy.generateFakeTvShowData()
    private val courseIdTv = courseResponsesTv[0].id



    @Test
    fun getAllMovies() {
        val dummyCourses = MutableLiveData<List<LocalData>>()
        dummyCourses.value = DataDummy.generateMovieData()
        Mockito.`when`(local.getAllLocalMovie()).thenReturn(dummyCourses)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllMovies())
        verify(local).getAllLocalMovie()
        assertNotNull(courseEntities.data)
        assertEquals(courseResponsesMovie.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvs() {
        val dummyCourses = MutableLiveData<List<LocalData>>()
        dummyCourses.value = DataDummy.generateTvShowData()
        Mockito.`when`(local.getAllLocalTv()).thenReturn(dummyCourses)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllTvShows())
        verify(local).getAllLocalTv()
        assertNotNull(courseEntities.data)
        assertEquals(courseResponsesMovie.size.toLong(), courseEntities.data?.size?.toLong())
    }

//
    @Test
    fun getTvWithId(){
        val dummyEntity = MutableLiveData<LocalData>()
        dummyEntity.value=DataDummy.generateDummyWithId(DataDummy.generateTvShowData()[0],false)
        `when` (local.getLocalDatabyId(courseIdTv)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithTv(courseIdTv))
        verify(local).getLocalDatabyId(courseIdTv)
        assertNotNull(courseEntities.data)
        assertEquals(courseResponsesTv[0].title, courseEntities.data?.title)
    }

    @Test
    fun getMovieWithId(){
        val dummyEntity = MutableLiveData<LocalData>()
        dummyEntity.value=DataDummy.generateDummyWithId(DataDummy.generateMovieData()[0],false)
        `when` (local.getLocalDatabyId(courseIdMovie)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithMovie(courseIdMovie))
        verify(local).getLocalDatabyId(courseIdMovie)
        assertNotNull(courseEntities.data)
        assertEquals(courseResponsesMovie[0].title, courseEntities.data?.title)
    }

    @Test
    fun getBookmarkedTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, LocalData>
        `when`(local.getBookmarkedTv()).thenReturn(dataSourceFactory)
        academyRepository.getBookmarkedTv()
        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShowData()))
        verify(local).getBookmarkedTv()
        assertNotNull(courseEntities)
        assertEquals(courseResponsesTv.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, LocalData>
        `when`(local.getBookmarkedMovie()).thenReturn(dataSourceFactory)
        academyRepository.getBookmarkedMovie()
        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovieData()))
        verify(local).getBookmarkedMovie()
        assertNotNull(courseEntities)
        assertEquals(courseResponsesMovie.size.toLong(), courseEntities.data?.size?.toLong())
    }

}
//    @Test
//    fun getCourseWithTv() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
//                .onAllTvShowsReceived(courseResponsesTv)
//            null
//        }.`when`(remote).getAllTvShows()
//        val resultCourse = LiveDataTestUtil.getValue(academyRepository.getCourseWithTv(courseIdTv))
//        verify<RemoteDataSource>(remote).getAllTvShows()
//        assertNotNull(resultCourse)
//        assertEquals(courseResponsesTv[0].title, resultCourse.data?.title)
//    }
//@Test
//    fun getCourseWithMovie() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
//                .onAllMoviesReceived(courseResponsesMovie)
//            null
//        }.`when`(remote).getAllMovies()
//
//        val resultCourse = LiveDataTestUtil.getValue(academyRepository.getCourseWithMovie(courseIdMovie))
//
//        verify<RemoteDataSource>(remote).getAllMovies()
//        assertNotNull(resultCourse)
//        assertEquals(courseResponsesMovie[0].title, resultCourse.data?.title)
//    }

//    @Test
//    fun getCourseWithModules() {
//        val dummyEntity = MutableLiveData<LocalData>()
//        dummyEntity.value = DataDummy.generateDummyWithId(DataDummy.generateMovieData()[0], false)
//        Mockito.`when`(local.getLocalDatabyId(courseIdMovie)).thenReturn(dummyEntity)
//
//        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))
//        verify(local).getCourseWithModules(courseId)
//        assertNotNull(courseEntities.data)
//        assertNotNull(courseEntities.data?.mCourse?.title)
//        assertEquals(courseResponses[0].title, courseEntities.data?.mCourse?.title)
//    }


//    @Test
//    fun getAllTv() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
//                .onAllTvShowsReceived(courseResponsesTv)
//            null
//        }.`when`(remote).getAllTvShows()
//        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllTvShows())
//        verify<RemoteDataSource>(remote).getAllTvShows()
//        assertNotNull(courseEntities)
//        assertEquals(courseResponsesTv.size.toLong(), courseEntities.data?.size?.toLong())
//    }


//    @Test
//    fun getAllMovie() {
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
//                .onAllMoviesReceived(courseResponsesMovie)
//            null
//        }.`when`(remote).getAllMovies()
//        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllMovies())
//        verify<RemoteDataSource>(remote).getAllMovies()
//        assertNotNull(courseEntities)
//        assertEquals(courseResponsesMovie.size.toLong(), courseEntities.data?.size?.toLong())
//    }
