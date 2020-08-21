package com.example.dicoding_jetpack.utils.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicoding_jetpack.data.source.CatalogRepository
import com.example.dicoding_jetpack.di.Injection
import com.example.dicoding_jetpack.ui.detail.DetailViewModel
import com.example.dicoding_jetpack.ui.favorit.movie.FavMovieViewModel
import com.example.dicoding_jetpack.ui.favorit.tvshow.FavTvShowViewModel
import com.example.dicoding_jetpack.ui.movie.MovieViewModel
import com.example.dicoding_jetpack.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mCatalogRepository) as T
            }
                modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                return FavMovieViewModel(mCatalogRepository) as T
            }
                modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                return FavTvShowViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}