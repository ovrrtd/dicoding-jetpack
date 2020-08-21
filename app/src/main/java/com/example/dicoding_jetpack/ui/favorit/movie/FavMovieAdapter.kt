package com.example.dicoding_jetpack.ui.favorit.movie
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.*
import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.data.source.local.entity.LocalData
import com.example.dicoding_jetpack.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.items_movie.view.*

class FavMovieAdapter : PagedListAdapter<LocalData, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {
//    private var listMovies = ArrayList<LocalData>()
//
//    fun setCourses(movies: List<LocalData>?) {
//        if (movies == null) return
//        this.listMovies.clear()
//        this.listMovies.addAll(movies)
//    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalData>() {
            override fun areItemsTheSame(oldItem: LocalData, newItem: LocalData): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: LocalData, newItem: LocalData): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(movie :LocalData){
            with(itemView){
                mv_item_title.text = movie.title
                mv_item_overview.text = movie.overview
                mv_item_genre.text = movie.releaseDate

                with(itemView.context)
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



    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return ViewHolder(view)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
//    override fun getItemCount(): Int {
//        return listMovies.size
//    }

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
//    override fun onBindViewHolder(holder: FavMovieAdapter.ViewHolder, position: Int) {
//        val movie =listMovies[position]
//        holder.bind(movie)
//    }
    override fun onBindViewHolder(holder: FavMovieAdapter.ViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }
}