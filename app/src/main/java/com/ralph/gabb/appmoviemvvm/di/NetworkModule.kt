package com.ralph.gabb.appmoviemvvm.di

import com.ralph.gabb.appmoviemvvm.network.MovieService
import org.koin.dsl.module.module

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */

val networkModule = module{


    single {  MovieService() }

}