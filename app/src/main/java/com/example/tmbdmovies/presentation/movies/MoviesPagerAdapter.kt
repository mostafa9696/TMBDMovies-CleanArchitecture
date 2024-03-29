package com.example.tmbdmovies.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmbdmovies.data.Constants
import com.example.tmbdmovies.databinding.ItemMovieBinding
import com.example.tmbdmovies.presentation.model.MoviePresentation

class MoviesPagerAdapter(var onMovieClick: (movie: MoviePresentation) -> Unit) :
    PagingDataAdapter<MoviePresentation, MoviesPagerAdapter.ViewHolder>(diffUtil) {

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

        fun bind(currentMovie: MoviePresentation) {
            binding.apply {
                val posterUrl = Constants.base_img_url + currentMovie.posterPath
                Glide.with(itemView).load(posterUrl)
                    .transition(DrawableTransitionOptions.withCrossFade()).into(moviePosterIv)
                movieNameTv.text = currentMovie.title
                ratingTv.text = currentMovie.voteAverage.toString()
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

    class DiffUtilCallBack : DiffUtil.ItemCallback<MoviePresentation>() {
        override fun areItemsTheSame(oldItem: MoviePresentation, newItem: MoviePresentation) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MoviePresentation, newItem: MoviePresentation) =
            oldItem == newItem

    }
}