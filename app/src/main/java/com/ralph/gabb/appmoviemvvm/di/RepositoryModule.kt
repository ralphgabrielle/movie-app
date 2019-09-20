package com.ralph.gabb.appmoviemvvm.di

import com.ralph.gabb.appmoviemvvm.data.data_source.MovieDataSource
import com.ralph.gabb.appmoviemvvm.data.data_source.MovieDataSourceImpl
import com.ralph.gabb.appmoviemvvm.data.repository.MovieRepository
import com.ralph.gabb.appmoviemvvm.data.repository.MovieRepositoryImpl
import org.koin.dsl.module.module

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */

val repositoryModule = module {

    factory<MovieRepository> { MovieRepositoryImpl(get()) }

    factory<MovieDataSource> { MovieDataSourceImpl(get()) }

}