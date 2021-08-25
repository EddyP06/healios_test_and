package com.eddy.healiostest.data.models.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eddy.healiostest.ui.extension.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "userId")
    val userId: Long,
    @ColumnInfo(name = "title")
    val title: String = emptyString(),
    @ColumnInfo(name = "body")
    val body: String = emptyString()
): Parcelable
