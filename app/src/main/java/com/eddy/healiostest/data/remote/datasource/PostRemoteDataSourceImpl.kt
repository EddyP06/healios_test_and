package com.eddy.healiostest.data.remote.datasource

import com.eddy.healiostest.data.models.remote.PostResponse
import com.eddy.healiostest.data.remote.network.JsonPlaceholderApiClient
import retrofit2.Response

internal class PostRemoteDataSourceImpl(
    private val apiClient: JsonPlaceholderApiClient
) : PostsRemoteDataSource {

    override suspend fun getAllPosts(): Response<List<PostResponse>> =
        apiClient.getAllPosts()
}