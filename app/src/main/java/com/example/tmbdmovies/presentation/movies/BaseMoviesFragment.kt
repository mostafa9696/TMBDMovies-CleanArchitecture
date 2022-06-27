package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.presentation.MoviesLoadStateAdapter
import com.example.tmbdmovies.presentation.extensions.hide
import com.example.tmbdmovies.presentation.extensions.onStates
import com.example.tmbdmovies.presentation.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
abstract class BaseMoviesFragment(private val query: String) : Fragment() {

    protected lateinit var adapter: MoviesPagerAdapter
    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesRv()
        getMovies()
    }

    private fun getMovies() {
        viewModel.getMovies(query)

        /*
        The crucial difference with collectLatest from collect is that
        when the original flow emits a new value then the action block for the previous value is cancelled.
         */
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    abstract fun getMoviesRv(): RecyclerView
    abstract fun getProgressLoading(): ProgressBar

    private fun initMoviesRv() {

        adapter = MoviesPagerAdapter() {

        }

        val gridLayoutManager = GridLayoutManager(context, 2)
        getMoviesRv().layoutManager = gridLayoutManager
        getMoviesRv().adapter = adapter.withLoadStateFooter(
            footer = MoviesLoadStateAdapter { adapter.retry() }
        )

        adapter.onStates(
            hasHeader = false,
            onInitialLoading = {
                getProgressLoading().show()
                Log.d("bb0", "onInitialLoading: ")
            },
            onInitialEmpty = {
                Log.d("bb0", "onInitialEmpty: ")
            },
            onInitialError = {  // todo handle general error and network error
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                getProgressLoading().hide()
                Log.d("bb0", "onInitialError: ")
            }
        ) {
            Log.d("bb0", "Load new pager: ")
            getProgressLoading().hide()
        }

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount) 2 else 1
            }

        }
    }
}