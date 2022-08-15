package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tmbdmovies.R
import com.example.tmbdmovies.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoviesBinding
    var movieFragment: BaseMoviesFragment = PopularFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationMenu()
    }

    private fun initBottomNavigationMenu() {

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popular -> replaceFragment(PopularFragment())
                R.id.tv -> replaceFragment(TvSeriesFragment())
                R.id.top_rated -> replaceFragment(TopRatedFragment())
            }
            true
        }

        binding.bottomNavigation.selectedItemId = R.id.popular
    }

    private fun replaceFragment(movieFragment: BaseMoviesFragment) {
        this.movieFragment = movieFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, movieFragment).commit()
    }
}