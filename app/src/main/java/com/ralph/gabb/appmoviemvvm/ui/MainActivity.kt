package com.ralph.gabb.appmoviemvvm.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.base.BaseActivity
import com.ralph.gabb.appmoviemvvm.data.Movie
import com.ralph.gabb.appmoviemvvm.interactor.MainViewInteractor
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

class MainActivity : BaseActivity(), MainViewInteractor {

    private val viewModel: MainViewModel by viewModel()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun viewCreated() {
        viewModel.setViewInteractor(this)

        setUpUI()
        startObserving()
        setUpBackground()
    }

    private fun setUpBackground() {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg")
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) { }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) { }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    plantLog("onBitmapLoaded")
                    bitmap?.let {
                        ivBackground.setImageBitmap(BlurBuilder.blur(this@MainActivity, it))
                    }
                }
            })
    }

    private fun setUpUI() {
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun startObserving() = CoroutineScope(Dispatchers.Main).launch {
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
    }

    private fun displayNowShowingMovies(result: List<Movie>) {
        rvMovies.adapter = NowShowingMovieAdapter(this, result)
    }

    private fun displayUpcomingMovies(result: List<Movie>) {
        rvUpcoming.adapter = UpcomingMovieAdapter(this, result)
    }
}
