package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.data.MoviesPagingSource.Companion.TOP_RATED
import com.example.tmbdmovies.databinding.FragmentTopRatedBinding

class TopRatedFragment : BaseMoviesFragment(TOP_RATED) {
    lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getMoviesRv(): RecyclerView = binding.moviesRv

    override fun getProgressLoading(): ProgressBar = binding.progressbar

    override fun getNoInternetView(): ConstraintLayout = binding.noInternetContainer.root

    override fun getRetryButton(): Button = binding.noInternetContainer.noInternetBtn
}