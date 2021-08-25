package com.eddy.healiostest.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eddy.healiostest.ui.extension.emptyString
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String = emptyString(),
    @ColumnInfo(name = "username")
    val username: String = emptyString(),
    @ColumnInfo(name = "email")
    val email: String = emptyString()
)
