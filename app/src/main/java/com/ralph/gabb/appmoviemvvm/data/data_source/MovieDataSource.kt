package com.ralph.gabb.appmoviemvvm.data.data_source

import androidx.lifecycle.LiveData
import com.ralph.gabb.appmoviemvvm.data.MovieResult

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
interface MovieDataSource {

    suspend fun fetchNowShowingMovies(): LiveData<out MovieResult>

    suspend fun fetchUpcomingMovies(): LiveData<out MovieResult>
}