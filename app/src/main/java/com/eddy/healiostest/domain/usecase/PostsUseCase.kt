package com.eddy.healiostest.domain.usecase

import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostsUseCase {
    fun getAllPosts(): Flow<Resource<List<PostEntity>>>
}