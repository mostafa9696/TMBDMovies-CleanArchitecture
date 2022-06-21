package com.example.tmbdmovies.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.data.models.Movie
import com.example.tmbdmovies.databinding.ItemMovieBinding

class MoviesPagerAdapter(var onMovieClick: (movie: Movie) -> Unit) :
    PagingDataAdapter<Movie, MoviesPagerAdapter.ViewHolder>(diffUtil) {

    lateinit var binding: ItemMovieBinding

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                    val movie = getItem(absoluteAdapterPosition)!!
                    onMovieClick(movie)
                }
            }
        }

        fun bind(currentMovie: Movie) {
            binding.apply {
                /* val posterUrl = "https://image.tmdb.org/t/p/w500" + currentMovie.poster_path
                 Glide.with(itemView).load(posterUrl)
                     .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)*/
                text.text = currentMovie.original_title
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesPagerAdapter.ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesPagerAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        val diffUtil: DiffUtilCallBack = DiffUtilCallBack()
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem

    }
}