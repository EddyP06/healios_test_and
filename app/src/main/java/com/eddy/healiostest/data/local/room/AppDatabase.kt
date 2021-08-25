package com.eddy.healiostest.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eddy.healiostest.data.local.room.dao.CommentDao
import com.eddy.healiostest.data.local.room.dao.PostDao
import com.eddy.healiostest.data.local.room.dao.UserDao
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.entity.UserEntity

@Database(
    entities = [PostEntity::class, UserEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPostDao(): PostDao
    abstract fun getUserDao(): UserDao
    abstract fun getCommentDao(): CommentDao
}