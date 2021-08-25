package com.eddy.healiostest.data.local.datasource

import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.UserResponse
import kotlinx.coroutines.flow.Flow

interface DetailsLocalDataSource {
    fun getUserById(userId: Long): Flow<UserEntity?>
    suspend fun updateUser(userResponse: UserResponse): Long
    fun getPostComments(postId: Long): Flow<List<CommentEntity>>
    suspend fun updatePostComments(comments: List<CommentResponse>)
}