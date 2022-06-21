package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmbdmovies.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    val viewModel: MoviesViewModel by viewModels()
    lateinit var adapter: MoviesPagerAdapter
    lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MoviesPagerAdapter() {

        }

        binding.apply {
            moviesRv.setHasFixedSize(true)
            moviesRv.layoutManager = LinearLayoutManager(this@MoviesActivity)
            moviesRv.adapter = adapter
        }
        // todo compare this with lifecycleScope.launch {
        //            repeatOnLifecycle(Lifecycle.State.STARTED) {
        lifecycleScope.launchWhenStarted {
            viewModel.upcomingMovies.collect {
                adapter.submitData(it)
            }
        }
    }
}