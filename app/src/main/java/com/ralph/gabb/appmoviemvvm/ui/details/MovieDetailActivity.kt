package com.ralph.gabb.appmoviemvvm.ui.details

import androidx.lifecycle.Observer
import com.ralph.gabb.appmoviemvvm.R
import com.ralph.gabb.appmoviemvvm.base.BaseActivity
import com.ralph.gabb.appmoviemvvm.internal.plantLog
import com.ralph.gabb.appmoviemvvm.util.Constant.BASE_IMAGE
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MovieDetailActivity : BaseActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    override val layoutId: Int?
        get() = R.layout.activity_movie_detail

    override fun viewCreated() {
//        supportPostponeEnterTransition()

//        val fade = Fade()
//        fade.excludeTarget(android.R.id.statusBarBackground, true)
//        fade.excludeTarget(android.R.id.navigationBarBackground, true)
//        window.exitTransition = fade
//        window.enterTransition = fade

        intent.getStringExtra("movie")?.let {
            viewModel.setMovie(it)
        }

        displayImage()
    }

    private fun displayImage() {
        viewModel.movie.observe(this, Observer {

            val url = BASE_IMAGE + it.posterPath

            Picasso.get()
                .load(url)
                .noFade()
                .into(ivImage, object: Callback {
                    override fun onSuccess() {
//                        supportStartPostponedEnterTransition()
                    }

                    override fun onError(e: Exception?) {
//                        supportStartPostponedEnterTransition()
                    }
                })

            plantLog("Ralph Testing $url")
        })
    }
}