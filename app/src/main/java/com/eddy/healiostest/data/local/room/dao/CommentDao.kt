package com.eddy.healiostest.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eddy.healiostest.data.models.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<CommentEntity>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsByPostId(postId: Long): Flow<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(posts: List<CommentEntity>)
}