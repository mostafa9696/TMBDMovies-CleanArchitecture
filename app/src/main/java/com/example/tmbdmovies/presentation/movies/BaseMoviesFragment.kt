package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.presentation.MoviesLoadStateAdapter
import com.example.tmbdmovies.presentation.details.MovieDetailsActivity
import com.example.tmbdmovies.presentation.extensions.hide
import com.example.tmbdmovies.presentation.extensions.onStates
import com.example.tmbdmovies.presentation.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.net.UnknownHostException

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
    abstract fun getNoInternetView(): ConstraintLayout
    abstract fun getRetryButton(): Button

    private fun initMoviesRv() {

        adapter = MoviesPagerAdapter() {
            it.type = query
            MovieDetailsActivity.start(requireContext(), it)
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
            },
            onInitialEmpty = {
            },
            onInitialError = {  // todo handle general error and network error
                if (it is UnknownHostException)
                    getNoInternetView().show()
                else
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                getProgressLoading().hide()
            }
        ) {
            getNoInternetView().hide()
            getProgressLoading().hide()
        }

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount) 2 else 1
            }

        }

        getRetryButton().setOnClickListener {
            viewModel.getMovies(query + "1")
        }
    }
}