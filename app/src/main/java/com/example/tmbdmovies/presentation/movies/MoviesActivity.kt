package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tmbdmovies.R
import com.example.tmbdmovies.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoviesBinding
    var popularFragment: BaseMoviesFragment = PopularFragment()
    var tvSeriesFragment: BaseMoviesFragment = TvSeriesFragment()
    var topRatedFragment: BaseMoviesFragment = TopRatedFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationMenu()
    }

    private fun initBottomNavigationMenu() {

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popular -> replaceFragment(popularFragment)
                R.id.tv -> replaceFragment(tvSeriesFragment)
                R.id.top_rated -> replaceFragment(topRatedFragment)
            }
            true
        }

        binding.bottomNavigation.selectedItemId = R.id.popular
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, fragment).commit()
    }
}