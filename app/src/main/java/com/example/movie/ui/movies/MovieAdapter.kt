package com.example.movie.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.movie.data.entities.Movie
import com.example.movie.databinding.MovieItemBinding

class MovieAdapter (private val listener: MovieItemListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieItemListener {
        fun onMovieClick(movieId: String)
    }

    private val items = ArrayList<Movie>()

    fun setItems(items: ArrayList<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: MovieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])
}

class MovieViewHolder(private val itemBinding: MovieItemBinding, private val listener: MovieAdapter.MovieItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: Movie

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Movie) {
        this.movie = item
        itemBinding.titleTv.text = item.title
        itemBinding.typeTv.text = item.type
        itemBinding.yearTv.text = item.year
        Glide.with(itemBinding.root)
            .load(item.poster)
            .centerCrop()
            .into(itemBinding.posterIv)
    }

    override fun onClick(v: View?) {
        listener.onMovieClick(movie.imdbID)
    }
}