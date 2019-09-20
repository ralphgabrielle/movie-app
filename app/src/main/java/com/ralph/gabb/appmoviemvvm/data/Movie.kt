package com.ralph.gabb.appmoviemvvm.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
data class Movie(

    var id: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("original_title")
    var originalTitle: String,

    var overview: String,

    @SerializedName("release_date")
    var releaseData: String,

    @SerializedName("vote_average")
    var voteAverage: Double

)