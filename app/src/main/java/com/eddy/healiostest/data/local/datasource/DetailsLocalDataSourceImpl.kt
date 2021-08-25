package com.eddy.healiostest.data.local.datasource

import com.eddy.healiostest.data.local.room.dao.CommentDao
import com.eddy.healiostest.data.local.room.dao.UserDao
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.models.mappers.commentToEntity
import com.eddy.healiostest.data.models.mappers.userToEntity
import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.UserResponse
import kotlinx.coroutines.flow.Flow

internal class DetailsLocalDataSourceImpl(
    private val userDao: UserDao,
    private val commentDao: CommentDao
) : DetailsLocalDataSource {
    override  fun getUserById(userId: Long): Flow<UserEntity?> =
        userDao.getUserById(userId)

    override suspend fun updateUser(userResponse: UserResponse): Long =
        userDao.insertUser(userToEntity(userResponse))

    override  fun getPostComments(postId: Long): Flow<List<CommentEntity>> =
        commentDao.getCommentsByPostId(postId)

    override suspend fun updatePostComments(comments: List<CommentResponse>) =
        commentDao.insertComments(comments.map { commentToEntity(it) })
}