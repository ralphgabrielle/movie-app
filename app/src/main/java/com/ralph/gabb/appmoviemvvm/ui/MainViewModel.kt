package com.ralph.gabb.appmoviemvvm.ui

import com.ralph.gabb.appmoviemvvm.base.BaseViewModel
import com.ralph.gabb.appmoviemvvm.data.repository.MovieRepository
import com.ralph.gabb.appmoviemvvm.interactor.MainViewInteractor
import com.ralph.gabb.appmoviemvvm.internal.lazyDeferred

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MainViewModel(private var movieRepository: MovieRepository) : BaseViewModel<MainViewInteractor>() {

    val nowShowingMovies by lazyDeferred {
        movieRepository.fetchNowShowingMovies()
    }

    val upcomingMovies by lazyDeferred {
        movieRepository.fetchUpComingMovies()
    }

}