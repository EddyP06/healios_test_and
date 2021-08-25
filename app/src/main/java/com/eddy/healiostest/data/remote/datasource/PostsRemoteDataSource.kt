package com.eddy.healiostest.data.remote.datasource

import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.remote.PostResponse
import retrofit2.Response

interface PostsRemoteDataSource {
    suspend fun getAllPosts(): Response<List<PostResponse>>
}