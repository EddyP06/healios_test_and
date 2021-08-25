package com.eddy.healiostest.data.models.mappers

import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.models.remote.CommentResponse
import com.eddy.healiostest.data.models.remote.PostResponse
import com.eddy.healiostest.data.models.remote.UserResponse

fun postToEntity(postResponse: PostResponse): PostEntity = PostEntity(
    id = postResponse.id,
    userId = postResponse.userId,
    title = postResponse.title,
    body = postResponse.body
)

fun userToEntity(userResponse: UserResponse): UserEntity = UserEntity(
    id = userResponse.id,
    name = userResponse.name,
    username = userResponse.username,
    email = userResponse.email
)

fun commentToEntity(commentResponse: CommentResponse): CommentEntity = CommentEntity(
    id = commentResponse.id,
    postId = commentResponse.postId,
    name = commentResponse.name,
    body = commentResponse.body,
    email = commentResponse.email
)