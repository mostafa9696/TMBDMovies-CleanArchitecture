package com.example.tmbdmovies.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmbdmovies.data.Constants
import com.example.tmbdmovies.databinding.ItemMovieCastBinding
import com.example.tmbdmovies.presentation.model.MovieCastPresentation

class MovieCastAdapter(var cast: List<MovieCastPresentation>) :
    RecyclerView.Adapter<MovieCastAdapter.CastViewHolder>() {
    inner class CastViewHolder(var binding: ItemMovieCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieCastPresentation: MovieCastPresentation) {
            binding.apply {
                Glide.with(binding.root)
                    .load(Constants.base_img_url + movieCastPresentation.profileImageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(castImageView)

                castName.text = cast[absoluteAdapterPosition].name
                castCharacterName.text = cast[absoluteAdapterPosition].characterFilmName
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCastAdapter.CastViewHolder {
        val binding =
            ItemMovieCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastAdapter.CastViewHolder, position: Int) {
        if (position != -1) {
            holder.bind(cast[position])
        }
    }

    override fun getItemCount(): Int {
        return cast.size
    }
}