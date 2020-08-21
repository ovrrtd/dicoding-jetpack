package com.example.dicoding_jetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.utils.DataDummy
import com.example.dicoding_jetpack.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<LocalData>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }
    @Test
    fun getMovies() {
        val dummyCourses = Resource.success( DataDummy.generateMovieData())
        val courses = MutableLiveData<Resource<List<LocalData>>>()
        courses.value = dummyCourses

        `when`(catalogRepository.getAllMovies()).thenReturn(courses)
        val entities = viewModel.getMovies().value
        verify<CatalogRepository>(catalogRepository).getAllMovies()
        assertNotNull(entities)
        assertEquals(20, entities?.data?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}