package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.data.MoviesPagingSource.Companion.POPULAR
import com.example.tmbdmovies.databinding.FragmentPopularBinding

class PopularFragment : BaseMoviesFragment(POPULAR) {

    lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getMoviesRv(): RecyclerView = binding.moviesRv

    override fun getProgressLoading(): ProgressBar = binding.progressbar

}