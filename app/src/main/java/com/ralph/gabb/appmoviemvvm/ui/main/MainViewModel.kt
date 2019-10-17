package com.ralph.gabb.appmoviemvvm.ui.main

import androidx.lifecycle.ViewModel
import com.ralph.gabb.appmoviemvvm.data.repository.MovieRepository
import com.ralph.gabb.appmoviemvvm.internal.lazyDeferred

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MainViewModel(private var movieRepository: MovieRepository) : ViewModel() {

    val nowShowingMovies by lazyDeferred {
        movieRepository.fetchNowShowingMovies()
    }

    val upcomingMovies by lazyDeferred {
        movieRepository.fetchUpComingMovies()
    }

    val mockApi by lazyDeferred {
        movieRepository.mockApi()
    }

}