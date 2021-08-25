package com.eddy.healiostest.data.remote.datasource

import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.UserResponse
import com.eddy.healiostest.data.remote.network.JsonPlaceholderApiClient
import retrofit2.Response

internal class DetailsRemoteDataSourceImpl(
    private val apiClient: JsonPlaceholderApiClient
) : DetailsRemoteDataSource {
    override suspend fun getUserById(userId: Long): Response<List<UserResponse>> =
        apiClient.getUserByPost(userId)

    override suspend fun getPostComments(postId: Long): Response<List<CommentResponse>> =
        apiClient.getPostComments(postId)
}