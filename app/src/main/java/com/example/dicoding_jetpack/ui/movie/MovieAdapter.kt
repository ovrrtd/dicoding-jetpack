package com.example.dicoding_jetpack.ui.movie
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.items_movie.view.*
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<LocalData>()

    fun setCourses(movies: List<LocalData>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return ViewHolder(view)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return listMovies.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie =listMovies[position]
        holder.bind(movie)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(movie :LocalData){
            with(itemView){
                mv_item_title.text = movie.title
                mv_item_overview.text = movie.overview
                mv_item_genre.text = movie.releaseDate

                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .into(img_poster_mv)

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, true)
                    itemView.context.startActivity(intent)
                }
               }
        }

    }
}