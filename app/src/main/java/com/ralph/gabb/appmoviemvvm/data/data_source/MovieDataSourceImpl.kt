package com.ralph.gabb.appmoviemvvm.data.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ralph.gabb.appmoviemvvm.data.MovieResult
import com.ralph.gabb.appmoviemvvm.internal.plantLog
import com.ralph.gabb.appmoviemvvm.network.MovieService

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MovieDataSourceImpl(private var movieService: MovieService): MovieDataSource {

    private val fetchedNowShowingMovies = MutableLiveData<MovieResult>()
    private val fetchedUpcomingMovies = MutableLiveData<MovieResult>()

    override suspend fun fetchNowShowingMovies(): LiveData<out MovieResult> {
        val movies = movieService.fetchNowShowingMovies()
        fetchedNowShowingMovies.postValue(movies)
        return fetchedNowShowingMovies
    }

    override suspend fun fetchUpcomingMovies(): LiveData<out MovieResult> {
        val movies = movieService.fetchUpcomingMovies()
        fetchedUpcomingMovies.postValue(movies)
        return fetchedUpcomingMovies
    }
}