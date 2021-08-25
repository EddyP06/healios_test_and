package com.eddy.healiostest.ui

import android.app.Application
import com.eddy.healiostest.BuildConfig.BASE_URL
import com.eddy.healiostest.data.local.di.localModule
import com.eddy.healiostest.data.remote.di.remoteModule
import com.eddy.healiostest.data.repository.di.repositoryModule
import com.eddy.healiostest.domain.di.useCaseModule
import com.eddy.healiostest.ui.features.details.di.detailModule
import com.eddy.healiostest.ui.features.posts.di.postsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    localModule,
                    remoteModule(BASE_URL),
                    repositoryModule,
                    useCaseModule,
                    postsModule,
                    detailModule
                )
            )
        }
    }
}