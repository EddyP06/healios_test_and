package com.eddy.healiostest.data.remote.di

import com.eddy.healiostest.data.remote.datasource.DetailsRemoteDataSource
import com.eddy.healiostest.data.remote.datasource.DetailsRemoteDataSourceImpl
import com.eddy.healiostest.data.remote.datasource.PostRemoteDataSourceImpl
import com.eddy.healiostest.data.remote.datasource.PostsRemoteDataSource
import com.eddy.healiostest.data.remote.network.JsonPlaceholderApiClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun remoteModule(baseUrl: String) = module {
    factory<Interceptor> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(JsonPlaceholderApiClient::class.java)
    }

    factory<PostsRemoteDataSource> {
        PostRemoteDataSourceImpl(
            apiClient = get()
        )
    }

    factory<DetailsRemoteDataSource> {
        DetailsRemoteDataSourceImpl(
            apiClient = get()
        )
    }

}