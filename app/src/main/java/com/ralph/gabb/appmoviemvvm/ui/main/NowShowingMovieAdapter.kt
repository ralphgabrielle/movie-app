package com.ralph.gabb.appmoviemvvm.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movies.view.*

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class NowShowingMovieAdapter(private val context: Context,
                             private val movies: List<Movie>,
                             private val selectMovie: SelectMovie
): RecyclerView.Adapter<NowShowingMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        LayoutInflater.from(context).inflate(R.layout.row_movies, parent, false).let {
            return MovieViewHolder(it)
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.insertImage(BASE_IMAGE + movie.posterPath)

        holder.addMovieEvent(selectMovie, movie)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun insertImage(url: String) {
            Picasso.get()
                .load(url)
                .into(itemView.ivImage)
        }

        fun addMovieEvent(selectMovie: SelectMovie, movie: Movie) {
            itemView.ivImage.setOnClickListener {
                selectMovie.onSelectMovie(movie)
            }
        }
    }

    companion object {
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}