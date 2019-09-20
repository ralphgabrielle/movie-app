package com.ralph.gabb.appmoviemvvm.network

import com.ralph.gabb.appmoviemvvm.data.MovieResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */

interface MovieService {

    @GET("movie/now_playing")
    suspend fun fetchNowShowingMovies(@Query("api_key") key: String = API_KEY,
                                      @Query("language") languageCode: String = "en-US"): MovieResult

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(@Query("api_key") key: String = API_KEY,
                                    @Query("language") languageCode: String = "en-US"): MovieResult

    companion object {
        operator fun invoke(): MovieService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }

        const val API_KEY = "410d39161bfac88a09d369676a329852"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

}