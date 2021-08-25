package com.eddy.healiostest.data.local.datasource

import com.eddy.healiostest.data.local.room.dao.PostDao
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.mappers.postToEntity
import com.eddy.healiostest.data.models.remote.PostResponse
import kotlinx.coroutines.flow.Flow

internal class PostsLocalDataSourceImpl(
    private val postDao: PostDao
) : PostsLocalDataSource {
    override fun getAllPosts(): Flow<List<PostEntity>> =
        postDao.getAllPosts()

    override suspend fun updatePosts(posts: List<PostResponse>) =
        postDao.insertPosts(posts = posts.map {  postToEntity(it) })
}