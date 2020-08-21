package com.example.dicoding_jetpack.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.ui.favorit.movie.FavMovieFragment
import com.example.dicoding_jetpack.ui.favorit.tvshow.FavTvShowFragment
import com.example.dicoding_jetpack.ui.movie.MovieFragment
import com.example.dicoding_jetpack.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show,
            R.string.fav_movie,
            R.string.fav_tv
        )
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            2-> FavMovieFragment()
            3-> FavTvShowFragment()
            else -> Fragment()
        }


    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(
        TAB_TITLES[position]
    )


}