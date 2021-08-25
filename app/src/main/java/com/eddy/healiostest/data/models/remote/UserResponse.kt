package com.eddy.healiostest.data.models.remote

import com.eddy.healiostest.ui.extension.emptyString
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String = emptyString(),
    @SerializedName("username")
    val username: String = emptyString(),
    @SerializedName("email")
    val email: String = emptyString()
)
