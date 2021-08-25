package com.eddy.healiostest.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eddy.healiostest.ui.extension.emptyString
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "postId")
    val postId: Long,
    @ColumnInfo(name = "name")
    val name: String = "Anonymous",
    @ColumnInfo(name = "email")
    val email: String = emptyString(),
    @ColumnInfo(name = "body")
    val body: String = emptyString(),

)
