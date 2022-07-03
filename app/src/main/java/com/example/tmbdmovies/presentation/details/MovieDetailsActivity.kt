package com.example.tmbdmovies.presentation.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.Constants
import com.example.tmbdmovies.data.Constants.YOUTUBE_VIDEO_URL
import com.example.tmbdmovies.databinding.ActivityMovieDetailsBinding
import com.example.tmbdmovies.databinding.ItemMovieGenreBinding
import com.example.tmbdmovies.presentation.extensions.hide
import com.example.tmbdmovies.presentation.extensions.remove
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import com.example.tmbdmovies.presentation.model.MoviePresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var moviePresentation: MoviePresentation
    private val viewModel: MovieDetailsViewModel by viewModels()

    lateinit var movieTrailersAdapter: MovieTrailersAdapter
    lateinit var movieCastAdapter: MovieCastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        moviePresentation = intent.getParcelableExtra(MOVIE_EXTRA)!!
        viewModel.getMovieInfo(moviePresentation)

        observeOnMovieCast()
    }

    private fun observeOnMovieCast() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieCastStateFlow.collect {
                    when (it) {
                        is Resource.Success -> setMovieDataViews(it.data)
                        is Resource.Error -> Toast.makeText(
                            this@MovieDetailsActivity,
                            it.exception.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieDataViews(movie: MoviePresentation?) = with(binding) {
        binding.progressBar.remove()
        movie?.apply {
            toolbar.title = title
            overViewTv.text = movie.overview

            val averagePercentage = (voteAverage?.times(10))?.toInt()
            movieRatingProgress.progress = averagePercentage ?: 0
            movieRatingTv.text = averagePercentage.toString()

            releaseDateTv.text = "Released on ${movie.releaseDate}"


            Glide.with(this@MovieDetailsActivity)
                .load(Constants.base_img_url + movie.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(moviePoster)

            Glide.with(this@MovieDetailsActivity)
                .load(Constants.base_img_url + movie.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(movieBackdropPoster)

            setMovieCast(movieCast)
            setMovieTrailers(movieTrailers)
            setMovieGenres(movieGenres)
        }
    }

    private fun setMovieGenres(movieGenres: List<String>?) {
        movieGenres?.forEach {
            val genreBinding = ItemMovieGenreBinding.inflate(layoutInflater)
            val genrechip = genreBinding.root
            genrechip.text = it
            binding.genreChipGroup.addView(genrechip)
        }
    }

    private fun setMovieTrailers(movieTrailers: List<MovieTrailerPresentation>?) {
        if (movieTrailers.isNullOrEmpty())
            binding.trailersGroup.hide()
        else {
            movieTrailersAdapter = MovieTrailersAdapter(movieTrailers) {
                val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$YOUTUBE_VIDEO_URL${it}"))
                startActivity(youtubeIntent)
            }
            binding.trailersRv.adapter = movieTrailersAdapter
        }
    }

    private fun setMovieCast(movieCast: List<MovieCastPresentation>?) {
        if (movieCast.isNullOrEmpty()) {
            binding.movieCastGroup.hide()
        } else {
            movieCastAdapter = MovieCastAdapter(movieCast)
            binding.movieCastRecyclerView.adapter = movieCastAdapter
        }
    }

    companion object {

        private const val MOVIE_EXTRA = "movie"

        fun start(context: Context, moviePresentation: MoviePresentation) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, moviePresentation)
            context.startActivity(intent)
        }
    }
}