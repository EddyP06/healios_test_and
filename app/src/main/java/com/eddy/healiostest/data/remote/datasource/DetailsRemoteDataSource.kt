package com.eddy.healiostest.data.remote.datasource

import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.UserResponse
import retrofit2.Response

interface DetailsRemoteDataSource {
    suspend fun getUserById(userId: Long): Response<List<UserResponse>>
    suspend fun getPostComments(postId: Long): Response<List<CommentResponse>>
}