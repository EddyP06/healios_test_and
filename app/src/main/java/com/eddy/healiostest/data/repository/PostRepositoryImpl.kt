package com.eddy.healiostest.data.repository

import com.eddy.healiostest.data.local.datasource.PostsLocalDataSource
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.remote.datasource.PostsRemoteDataSource
import com.eddy.healiostest.data.repository.util.Resource
import com.eddy.healiostest.data.repository.util.safeCachedApiCall
import kotlinx.coroutines.flow.Flow

internal class PostRepositoryImpl(
    private val postsRemoteDataSource: PostsRemoteDataSource,
    private val postsLocalDataSource: PostsLocalDataSource
) : PostsRepository {

    override fun getAllPosts(): Flow<Resource<List<PostEntity>>> = safeCachedApiCall(
        databaseSource = { postsLocalDataSource.getAllPosts() },
        apiCall = { postsRemoteDataSource.getAllPosts() },
        databaseEmitter = postsLocalDataSource::updatePosts,
    )

}