package com.ralph.gabb.appmoviemvvm.data.repository

import androidx.lifecycle.LiveData
import com.ralph.gabb.appmoviemvvm.data.MovieResult

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
interface MovieRepository {

    suspend fun fetchNowShowingMovies(): LiveData<out MovieResult>

    suspend fun fetchUpComingMovies(): LiveData<out MovieResult>

}