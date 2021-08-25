package com.eddy.healiostest.data.local.di

import androidx.room.Room
import com.eddy.healiostest.data.local.DATABASE_NAME
import com.eddy.healiostest.data.local.datasource.DetailsLocalDataSource
import com.eddy.healiostest.data.local.datasource.DetailsLocalDataSourceImpl
import com.eddy.healiostest.data.local.datasource.PostsLocalDataSource
import com.eddy.healiostest.data.local.datasource.PostsLocalDataSourceImpl
import com.eddy.healiostest.data.local.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().getPostDao()
    }

    single {
        get<AppDatabase>().getUserDao()
    }

    single {
        get<AppDatabase>().getCommentDao()
    }

    factory<PostsLocalDataSource> {
        PostsLocalDataSourceImpl(
            postDao = get()
        )
    }

    factory<DetailsLocalDataSource> {
        DetailsLocalDataSourceImpl(
            userDao = get(),
            commentDao = get()
        )
    }

}