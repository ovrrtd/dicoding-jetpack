package com.example.dicoding_jetpack.ui.favorit.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvShowViewModelTest{
    private lateinit var viewModel: FavTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository


    @Mock
    private lateinit var observer: Observer<PagedList<LocalData>>

    @Mock
    private lateinit var pagedList: PagedList<LocalData>

    @Before
    fun setUp() {
        viewModel = FavTvShowViewModel(catalogRepository)
    }

    @Test
    fun getBookmark() {
        val dummyCourses = pagedList
        `when`(dummyCourses.size).thenReturn(5)
        val courses = MutableLiveData<PagedList<LocalData>>()
        courses.value = dummyCourses

        `when`(catalogRepository.getBookmarkedTv()).thenReturn(courses)
        val courseEntities = viewModel.getTvShow().value
        verify<CatalogRepository>(catalogRepository).getBookmarkedTv()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}