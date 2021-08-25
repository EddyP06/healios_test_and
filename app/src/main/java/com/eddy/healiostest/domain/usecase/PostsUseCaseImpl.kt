package com.eddy.healiostest.domain.usecase

import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.repository.PostsRepository
import com.eddy.healiostest.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

internal class PostsUseCaseImpl(
    private val postsRepository: PostsRepository
) : PostsUseCase {

    override fun getAllPosts(): Flow<Resource<List<PostEntity>>> =
        postsRepository.getAllPosts()
}