package com.example.dicoding_jetpack.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.utils.viewmodel.ViewModelFactory
import com.example.dicoding_jetpack.vo.Status
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_ID = "extra_id"
    }

    internal lateinit var viewModel: DetailViewModel

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        setBookmarkState(false)
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            if (id != null) {
                val type = extras.getBoolean(EXTRA_TYPE)
                if (type != null) {
                    viewModel.setSelectedId(id, type)
                }
                progress_bar.visibility = View.VISIBLE
                if (type) {
                    viewModel.movieDetail.observe(this, Observer  { courseWithModuleResource ->
                        if (courseWithModuleResource != null) {
                            when (courseWithModuleResource.status) {
                                Status.LOADING -> progress_bar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                    populateMovie(courseWithModuleResource.data)
                                    setBookmarkState(courseWithModuleResource.data.bookmarked)
                                    progress_bar.visibility = View.GONE

                                }
                                Status.ERROR -> {
                                    progress_bar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                } else {
                    viewModel.tvDetail.observe(this, Observer  { courseWithModuleResource ->
                        if (courseWithModuleResource != null) {
                            when (courseWithModuleResource.status) {
                                Status.LOADING -> progress_bar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                    progress_bar.visibility = View.GONE
                                    populateTv(courseWithModuleResource.data)
                                    setBookmarkState(courseWithModuleResource.data.bookmarked)
                                }
                                Status.ERROR -> {
                                    progress_bar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }

            }
        }
    }

    fun populateMovie(data: LocalData) {
        vm_name.text = data.title
        vm_user_score.text = data.userScore
        vm_overview.text = data.overview

        Glide.with(this)
            .load(data.imagePath)
            .into(vm_photo)
        btnFav.setImageResource(R.drawable.ic_favorite_border_pink_24dp)
    }

    fun populateTv(data: LocalData) {
        vm_name.text = data.title
        vm_user_score.text = data.userScore
        vm_overview.text = data.overview

        Glide.with(this)
            .load(data.imagePath)
            .into(vm_photo)
        btnFav.setImageResource(R.drawable.ic_favorite_border_pink_24dp)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        bookmarkState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            if (viewModel.type) {
                println("cek bisa ini")
                viewModel.setBookmarkMovie()
                return true
            } else {
                println("cek bisa")
                viewModel.setBookmarkTv()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_pink_24dp)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_pink_24dp)
        }
    }

    private fun bookmarkState() {
        if (viewModel.type) {
            movieObserve()
        } else {
            tvObserve()
        }
    }

    private fun movieObserve() {
        viewModel.movieDetail.observe(this, Observer { courseWithModule ->
            if (courseWithModule != null) {
                when (courseWithModule.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (courseWithModule.data != null) {
                        val state = courseWithModule.data.bookmarked
                            setBookmarkState(state)
                        progress_bar.visibility = View.GONE

                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            }
        })
    }

    private fun tvObserve() {
        viewModel.tvDetail.observe(this, Observer { courseWithModule ->
            if (courseWithModule != null) {
                when (courseWithModule.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (courseWithModule.data != null) {
                        val state = courseWithModule.data.bookmarked
                        setBookmarkState(state)
                        progress_bar.visibility = View.GONE

                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            }
        })
    }
}
