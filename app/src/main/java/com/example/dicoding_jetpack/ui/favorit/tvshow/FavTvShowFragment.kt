package com.example.dicoding_jetpack.ui.favorit.tvshow

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
import kotlinx.android.synthetic.main.fragment_fav_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class FavTvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[FavTvShowViewModel::class.java]

            val academyAdapter = FavTvShowAdapter()

            progress_bar_fav_tv.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, Observer{ courses ->
                academyAdapter.submitList(courses)
                academyAdapter.notifyDataSetChanged()
                progress_bar_fav_tv.visibility = View.GONE

            })

            rv_tv_fav.layoutManager = LinearLayoutManager(context)
            rv_tv_fav.setHasFixedSize(true)
            rv_tv_fav.adapter = academyAdapter
        }
    }

}
