package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseMoviesFragment(private val query: String) : Fragment() {

    protected lateinit var adapter: MoviesPagerAdapter
    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRv()

        getMovies()
    }

    fun getMovies() {
        viewModel.getMovies(query)

        //        viewLifecycleOwner.lifecycleScope.launch {
        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect {
                adapter.submitData(it)
            }
        }
    }

    abstract fun getMoviesRv(): RecyclerView

    private fun initMoviesRv() {

        adapter = MoviesPagerAdapter() {

        }

        val gridLayoutManager = GridLayoutManager(context, 2)
        getMoviesRv().adapter = adapter
        getMoviesRv().layoutManager = gridLayoutManager
        /*recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { adapter.retry() },
            footer = MovieLoadStateAdapter { adapter.retry() },
        )*/

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount) 2 else 1
            }

        }
    }
}