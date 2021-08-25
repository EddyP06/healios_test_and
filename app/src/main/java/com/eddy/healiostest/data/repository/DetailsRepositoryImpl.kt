package com.eddy.healiostest.data.repository

import com.eddy.healiostest.data.local.datasource.DetailsLocalDataSource
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.remote.datasource.DetailsRemoteDataSource
import com.eddy.healiostest.data.repository.util.Resource
import com.eddy.healiostest.data.repository.util.safeCachedApiCall
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

internal class DetailsRepositoryImpl(
    private val detailsRemoteDataSource: DetailsRemoteDataSource,
    private val detailsLocalDataSource: DetailsLocalDataSource
) : DetailsRepository {

    override fun getUserData(userId: Long): Flow<Resource<UserEntity?>> = safeCachedApiCall(
        databaseSource = { detailsLocalDataSource.getUserById(userId) },
        apiCall = {
            val response = detailsRemoteDataSource.getUserById(userId)
            Response.success(
                response.code(),
                detailsRemoteDataSource.getUserById(userId).body()?.firstOrNull()
            )

        },
        databaseEmitter = detailsLocalDataSource::updateUser
    )

    override fun getComments(postId: Long): Flow<Resource<List<CommentEntity>>> = safeCachedApiCall(
        databaseSource = { detailsLocalDataSource.getPostComments(postId) },
        apiCall = { detailsRemoteDataSource.getPostComments(postId) },
        databaseEmitter = detailsLocalDataSource::updatePostComments
    )
}