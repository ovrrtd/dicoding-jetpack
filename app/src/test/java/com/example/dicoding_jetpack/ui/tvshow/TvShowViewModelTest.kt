package com.example.dicoding_jetpack.ui.tvshow

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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<LocalData>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getTvShow() {
        val dummyCourses =  Resource.success( DataDummy.generateTvShowData())
        val courses = MutableLiveData<Resource<List<LocalData>>>()
        courses.value = dummyCourses

        `when`(catalogRepository.getAllTvShows()).thenReturn(courses)
        val entities = viewModel.getTvShow().value
        verify<CatalogRepository>(catalogRepository).getAllTvShows()
        assertNotNull(entities)
        assertEquals(20, entities?.data?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}