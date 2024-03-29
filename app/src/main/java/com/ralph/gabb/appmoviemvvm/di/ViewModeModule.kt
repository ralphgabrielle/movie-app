package com.ralph.gabb.appmoviemvvm.di

import com.ralph.gabb.appmoviemvvm.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}