package com.ralph.gabb.appmoviemvvm.ui.details

import androidx.lifecycle.Observer
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.base.BaseActivity
import com.ralph.gabb.appmoviemvvm.util.Constant.BASE_IMAGE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MovieDetailActivity : BaseActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    override val layoutId: Int?
        get() = R.layout.activity_movie_detail

    override fun viewCreated() {
        intent.getStringExtra("movie")?.let {
            viewModel.setMovie(it)
        }

        displayImage()
    }

    private fun displayImage() {
        viewModel.movie.observe(this, Observer {
            Picasso.get()
                .load(BASE_IMAGE + it.backdropPath)
                .into(ivImage)
        })
    }
}