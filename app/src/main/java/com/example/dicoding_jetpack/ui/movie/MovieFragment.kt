package com.example.dicoding_jetpack.ui.movie

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
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
//            val courses = viewModel.getMovies()

            val academyAdapter = MovieAdapter()
//            academyAdapter.setCourses(courses)
            progress_bar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, Observer{ courses ->
                academyAdapter.setCourses(courses.data)
                academyAdapter.notifyDataSetChanged()
                progress_bar.visibility = View.GONE

            })

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = academyAdapter
        }
    }

}
