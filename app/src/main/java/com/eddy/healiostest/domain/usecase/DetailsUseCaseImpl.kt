package com.eddy.healiostest.domain.usecase

import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.repository.DetailsRepository
import com.eddy.healiostest.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

internal class DetailsUseCaseImpl(
    private val detailsRepository: DetailsRepository
) : DetailsUseCase {
    override fun getUserData(userId: Long): Flow<Resource<UserEntity?>> =
        detailsRepository.getUserData(userId)

    override fun getComments(postId: Long): Flow<Resource<List<CommentEntity>>> =
        detailsRepository.getComments(postId)
}