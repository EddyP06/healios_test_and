package com.eddy.healiostest.ui.features.details.di

import com.eddy.healiostest.ui.features.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel {
        DetailsViewModel(get(), get())
    }
}