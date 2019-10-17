package com.ralph.gabb.appmoviemvvm.data.repository

import androidx.lifecycle.LiveData
import com.ralph.gabb.appmoviemvvm.data.MockApi
import com.ralph.gabb.appmoviemvvm.data.MovieResult
import com.ralph.gabb.appmoviemvvm.data.data_source.MovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MovieRepositoryImpl(private var movieDataSource: MovieDataSource) : MovieRepository {

    override suspend fun mockApi(): LiveData<out MockApi> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.mockApi()
        }
    }

    override suspend fun fetchNowShowingMovies(): LiveData<out MovieResult> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.fetchNowShowingMovies()
        }
    }

    override suspend fun fetchUpComingMovies(): LiveData<out MovieResult> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.fetchUpcomingMovies()
        }
    }

}