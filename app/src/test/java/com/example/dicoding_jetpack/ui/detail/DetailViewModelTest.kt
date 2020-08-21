package com.example.dicoding_jetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.utils.DataDummy
import com.example.dicoding_jetpack.vo.Resource
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyCourseMovie = DataDummy.generateMovieData()[0]
    private val dummyCourseTv = DataDummy.generateTvShowData()[0]
    private val id = dummyCourseMovie.id
    private val idTv = dummyCourseTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository


    @Mock
    private lateinit var movieObserver: Observer<Resource<LocalData>>

    @Mock
    private lateinit var tvObserver:  Observer<Resource<LocalData>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getTvShowbyId() {
        val dummyCourseWithModule = Resource.success(DataDummy.generateDummyWithId(dummyCourseTv, true))
        val course = MutableLiveData<Resource<LocalData>>()
        viewModel.setSelectedId(idTv,false)
        course.value = dummyCourseWithModule

        `when`(catalogRepository.getCourseWithTv(idTv)).thenReturn(course)

        viewModel.tvDetail.observeForever(tvObserver)

        verify(tvObserver).onChanged(dummyCourseWithModule)
    }

//    @Test
//    fun getTvShow() {
//        val course = MutableLiveData<Resource<LocalData>>()
//        course.value = dummyCourseTv
//
//        `when`(catalogRepository.getCourseWithTv(idTv)).thenReturn(course)
//        viewModel.setSelectedId(dummyCourseTv.,type = false)
//        val entity = viewModel.tvDetail().value as TvShowData
//        verify(catalogRepository).getCourseWithTv(idTv)
//        println(entity)
//        assertNotNull(entity)
//        if (entity != null) {
//            assertEquals(dummyCourseTv.id, entity.id)
//            assertEquals(dummyCourseTv.title, entity.title)
//            assertEquals(dummyCourseTv.overview, entity.overview)
//            assertEquals(dummyCourseTv.imagePath, entity.imagePath)
//            assertEquals(dummyCourseTv.releaseDate, entity.releaseDate)
//        }
//        viewModel.getTvShow().observeForever(tvObserver)
//        verify(tvObserver).onChanged(dummyCourseTv)
//    }

    @Test
    fun getMoviebyId() {
        val dummyCourseWithModule = Resource.success(DataDummy.generateDummyWithId(dummyCourseMovie, true))
        val course = MutableLiveData<Resource<LocalData>>()
        viewModel.setSelectedId(id,true)
        course.value = dummyCourseWithModule



        `when`(catalogRepository.getCourseWithMovie(id)).thenReturn(course)

        viewModel.movieDetail.observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyCourseWithModule)
    }

//    @Test
//    fun getMovie() {
//        val course = MutableLiveData<MovieData>()
//        course.value = dummyCourseMovie
//
//        `when`(catalogRepository.getCourseWithMovie(id)).thenReturn(course)
//        viewModel.setSelectedId(dummyCourseMovie.id)
//        val entity = viewModel.getMovie().value as MovieData
////        verify(catalogRepository).getCourseWithMovie(id)
//        assertNotNull(entity)
//        if (entity != null) {
//            assertEquals(dummyCourseMovie.id, entity.id)
//            assertEquals(dummyCourseMovie.title, entity.title)
//            assertEquals(dummyCourseMovie.overview, entity.overview)
//            assertEquals(dummyCourseMovie.imagePath, entity.imagePath)
//            assertEquals(dummyCourseMovie.releaseDate, entity.releaseDate)
//        }
//        viewModel.getMovie().observeForever(movieObserver)
//        verify(movieObserver).onChanged(dummyCourseMovie)
//    }


}