package com.example.dicoding_jetpack.ui.favorit.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.utils.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.fragment_fav_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            val viewModel = ViewModelProvider(this,factory)[FavMovieViewModel::class.java]
//            val courses = viewModel.getMovies()

            val academyAdapter = FavMovieAdapter()
//            academyAdapter.setCourses(courses)
            progress_bar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, Observer{ courses ->

                academyAdapter.submitList(courses)
                academyAdapter.notifyDataSetChanged()
                progress_bar.visibility = View.GONE
            })

            rv_movie_fav.layoutManager = LinearLayoutManager(context)
            rv_movie_fav.setHasFixedSize(true)
            rv_movie_fav.adapter = academyAdapter
        }
    }

}
