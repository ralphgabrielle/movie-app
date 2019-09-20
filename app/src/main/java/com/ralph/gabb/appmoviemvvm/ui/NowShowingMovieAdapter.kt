package com.ralph.gabb.appmoviemvvm.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.data.Movie
import com.ralph.gabb.appmoviemvvm.internal.plantLog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movies.view.*

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class NowShowingMovieAdapter(private val context: Context,
                             private val movies: List<Movie>): RecyclerView.Adapter<NowShowingMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        LayoutInflater.from(context).inflate(R.layout.row_upcoming_movies, parent, false).let {
            return MovieViewHolder(it)
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.insertImage(BASE_IMAGE + movie.posterPath)
        plantLog(BASE_IMAGE + movie.posterPath)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun insertImage(url: String) {
            Picasso.get()
                .load(url)
                .into(itemView.ivImage)
        }
    }

    companion object {
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}