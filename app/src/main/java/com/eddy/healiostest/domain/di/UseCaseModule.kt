package com.eddy.healiostest.domain.di

import com.eddy.healiostest.domain.dispatchers.AppDispatchers
import com.eddy.healiostest.domain.usecase.DetailsUseCase
import com.eddy.healiostest.domain.usecase.DetailsUseCaseImpl
import com.eddy.healiostest.domain.usecase.PostsUseCase
import com.eddy.healiostest.domain.usecase.PostsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val useCaseModule = module {

    factory<PostsUseCase> {
        PostsUseCaseImpl(
            postsRepository = get()
        )
    }

    factory<DetailsUseCase> {
        DetailsUseCaseImpl(
            detailsRepository = get()
        )
    }

    factory {
        AppDispatchers(
            Dispatchers.IO,
            Dispatchers.Main,
            Dispatchers.Unconfined,
            Dispatchers.Default
        )
    }
}