package com.eddy.healiostest.data.repository

import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts(): Flow<Resource<List<PostEntity>>>
}