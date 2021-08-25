package com.eddy.healiostest.ui.features.posts.di

import com.eddy.healiostest.ui.features.posts.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postsModule = module {
    viewModel<PostViewModel> {
        PostViewModel(
            appDispatchers = get(),
            postsUseCase = get()
        )
    }
}