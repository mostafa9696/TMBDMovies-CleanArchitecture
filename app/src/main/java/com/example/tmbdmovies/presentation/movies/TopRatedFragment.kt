package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmbdmovies.R
import com.example.tmbdmovies.data.MoviesPagingSource.Companion.TOP_RATED
import com.example.tmbdmovies.databinding.FragmentTopRatedBinding
import com.example.tmbdmovies.databinding.FragmentTvSeriesBinding

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
}