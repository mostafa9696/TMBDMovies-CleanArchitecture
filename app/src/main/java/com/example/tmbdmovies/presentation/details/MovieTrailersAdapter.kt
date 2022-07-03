package com.example.tmbdmovies.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmbdmovies.data.Constants.YOUTUBE_THUMBNAIL_URL
import com.example.tmbdmovies.databinding.ItemMovieTrailerBinding
import com.example.tmbdmovies.presentation.model.MoviePresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation

class MovieTrailersAdapter(var trailers: List<MovieTrailerPresentation>,
                           var onVideoClick: (videoLink: String) -> Unit) :
    RecyclerView.Adapter<MovieTrailersAdapter.TrailerViewHolder>() {

    inner class TrailerViewHolder(var binding: ItemMovieTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieTrailerPresentation: MovieTrailerPresentation) {
            binding.apply {
                Glide.with(binding.root)
                    .load("$YOUTUBE_THUMBNAIL_URL${movieTrailerPresentation.key}/default.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(trailerThumb)

                trailerName.text = movieTrailerPresentation.name
                itemView.setOnClickListener {
                    onVideoClick(movieTrailerPresentation.key!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieTrailersAdapter.TrailerViewHolder {
        val binding =
            ItemMovieTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTrailersAdapter.TrailerViewHolder, position: Int) {
        if (position != -1) {
            holder.bind(trailers[position])
        }
    }

    override fun getItemCount(): Int {
        return trailers.size
    }
}