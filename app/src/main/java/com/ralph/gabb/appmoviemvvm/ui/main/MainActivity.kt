package com.ralph.gabb.appmoviemvvm.ui.main

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.base.BaseActivity
import com.ralph.gabb.appmoviemvvm.data.Movie
import com.ralph.gabb.appmoviemvvm.internal.plantLog
import com.ralph.gabb.appmoviemvvm.util.BlurBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.ralph.gabb.appmoviemvvm.ui.details.MovieDetailActivity


class MainActivity : BaseActivity(), SelectMovie {

    private val viewModel: MainViewModel by viewModel()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun viewCreated() {
        setUpUI()
        startObserving()
        setUpBackground()
    }

    private fun setUpUI() {
        setUpRecyclerView(rvMovies, false)
        setUpRecyclerView(rvUpcoming, true)
    }

    private fun setUpBackground() {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg")
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    plantLog("onBitmapLoaded")
                    bitmap?.let {
                        ivBackground.setImageBitmap(BlurBuilder.blur(this@MainActivity, it))
                    }
                }
            })
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView, isGrid: Boolean) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = if (!isGrid) LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        else GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
    }

    private fun startObserving() = CoroutineScope(Dispatchers.Main).launch {
        try {
//            val mockApi = viewModel.mockApi.await()

//            mockApi.observe(this@MainActivity, Observer {
//                if (it == null) return@Observer
//                plantLog(Gson().toJson(it))
//            })

            val nowShowingMovies = viewModel.nowShowingMovies.await()
            val upcomingMovies = viewModel.upcomingMovies.await()

            nowShowingMovies.observe(this@MainActivity, Observer { nowShowing ->
                if (nowShowing == null) return@Observer
                displayNowShowingMovies(nowShowing.results)
            })

            upcomingMovies.observe(this@MainActivity, Observer { upcoming ->
                if (upcoming == null) return@Observer
                displayUpcomingMovies(upcoming.results)
            })

        } catch (e: Exception) {
            plantLog("Ralph Testing - " + e.message)
        }
    }

    private fun displayNowShowingMovies(result: List<Movie>) {
        rvMovies.adapter = NowShowingMovieAdapter(this, result, this)
    }

    private fun displayUpcomingMovies(result: List<Movie>) {
        rvUpcoming.adapter = UpcomingMovieAdapter(this, result, this)
    }

    override fun onSelectMovie(movie: Movie, imageView: ImageView) {
        //
        // Open Detail Movie Window
        //
//
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, getString(R.string.transition_image))

        Intent(this, MovieDetailActivity::class.java).let {
            it.putExtra("movie", Gson().toJson(movie))
            startActivity(it, activityOptionsCompat.toBundle())
        }

//        val fade = Fade()
//        fade.excludeTarget(android.R.id.statusBarBackground, true)
//        fade.excludeTarget(android.R.id.navigationBarBackground, true)
//        window.exitTransition = fade
//        window.enterTransition = fade

//        startActivity<MovieDetailActivity>(
//            "movie" to Gson().toJson(movie)
//        )
    }
}
