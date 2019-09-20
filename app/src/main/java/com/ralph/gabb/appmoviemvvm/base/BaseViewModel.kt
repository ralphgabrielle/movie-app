package com.ralph.gabb.appmoviemvvm.base

import androidx.lifecycle.ViewModel
import com.igen.serino.base.BaseViewInteractor
import java.lang.ref.WeakReference

/**
 * Created by Ralph Gabrielle Orden on 9/6/2019.
 */
open class BaseViewModel<T> : ViewModel(), BaseViewInteractor {

    lateinit var navigator: WeakReference<T>

    fun getViewInteractor(): T {
        return navigator.get()!!
    }

    fun setViewInteractor(navigator: T) {
        this.navigator = WeakReference(navigator)
    }

    open fun messageReceive(message: String) {

    }

}