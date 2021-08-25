package com.eddy.healiostest.data.remote.network

import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.PostResponse
import com.eddy.healiostest.data.models.remote.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceholderApiClient {
    @GET(POSTS_ENDPOINT)
    suspend fun getAllPosts(): Response<List<PostResponse>>

    @GET(USERS_ENDPOINT)
    suspend fun getUserByPost(@Query("id") userId: Long): Response<List<UserResponse>>

    @GET(COMMENTS_ENDPOINT)
    suspend fun getPostComments(@Query("postId") postId: Long): Response<List<CommentResponse>>
}