package com.eddy.healiostest.data.local.datasource

import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.remote.PostResponse
import kotlinx.coroutines.flow.Flow

interface PostsLocalDataSource {
    fun getAllPosts(): Flow<List<PostEntity>>
    suspend fun updatePosts(posts: List<PostResponse>)
}