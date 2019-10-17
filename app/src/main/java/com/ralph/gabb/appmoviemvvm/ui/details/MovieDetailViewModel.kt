package com.ralph.gabb.appmoviemvvm.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ralph.gabb.appmoviemvvm.data.Movie

/**
 * Created by Ralph Gabrielle Orden on 10/17/2019.
 */
class MovieDetailViewModel : ViewModel() {

    var movie : MutableLiveData<Movie> = MutableLiveData()

    fun setMovie(movie: String) {
        this.movie.value = Gson().fromJson(movie, Movie::class.java)
    }


}