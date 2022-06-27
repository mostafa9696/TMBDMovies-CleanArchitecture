package com.example.tmbdmovies.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tmbdmovies.R
import com.example.tmbdmovies.databinding.ActivityMoviesBinding
import com.example.tmbdmovies.presentation.extensions.hide
import com.example.tmbdmovies.presentation.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityMoviesBinding
    var movieFragment: BaseMoviesFragment = PopularFragment()
    // todo try to fix resumed fragment with not pagination
/*    var popularFragment: BaseMoviesFragment = PopularFragment()
    var tvSeriesFragment: BaseMoviesFragment = TvSeriesFragment()
    var topRatedFragment: BaseMoviesFragment = TopRatedFragment()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationMenu()

        handleSearchComponent()
    }

    // todo make search for each fragment and save search query with results when navigate between tabs
    private fun handleSearchComponent() = with(binding) {
        searchIv.setOnClickListener {
            searchEt.show()
            toolbarTitleTv.hide()
        }

        searchEt.addTextChangedListener {

        }
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