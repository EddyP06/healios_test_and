package com.eddy.healiostest.data.repository.di

import com.eddy.healiostest.data.repository.DetailsRepository
import com.eddy.healiostest.data.repository.DetailsRepositoryImpl
import com.eddy.healiostest.data.repository.PostRepositoryImpl
import com.eddy.healiostest.data.repository.PostsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<PostsRepository> {
        PostRepositoryImpl(
            postsLocalDataSource = get(),
            postsRemoteDataSource = get()
        )
    }

    factory<DetailsRepository> {
        DetailsRepositoryImpl(
            detailsLocalDataSource = get(),
            detailsRemoteDataSource = get()
        )
    }
}