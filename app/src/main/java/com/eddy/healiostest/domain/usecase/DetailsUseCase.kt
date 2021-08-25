package com.eddy.healiostest.domain.usecase

import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsUseCase {
    fun getUserData(userId: Long): Flow<Resource<UserEntity?>>
    fun getComments(postId: Long): Flow<Resource<List<CommentEntity>>>
}