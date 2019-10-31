package com.ralph.gabb.appmoviemvvm.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Created by Ralph Gabrielle Orden on 9/3/2019.
 */
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun viewCreated()

    protected open val activityToolbar : Toolbar? = null

    @get:DrawableRes
    protected open val toolbarIconId: Int? = null

    @get:StringRes
    protected open val toolbarTitleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let {
            setContentView(it)
        }

        initToolbar()

        viewCreated()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        activityToolbar?.let { it ->
            setSupportActionBar(it)

            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                elevation = 8f

                toolbarTitleId?.let {
//                    tvToolbarTitle.text = getString(it)
                }

                toolbarIconId?.let {
                    setHomeAsUpIndicator(it)
                }
            }
        }
    }
}