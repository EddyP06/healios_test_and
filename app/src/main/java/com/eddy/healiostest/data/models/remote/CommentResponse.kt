package com.eddy.healiostest.data.models.remote

import com.eddy.healiostest.ui.extension.emptyString
import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("name")
    val name: String = "Anonymous",
    @SerializedName("email")
    val email: String = emptyString(),
    @SerializedName("body")
    val body: String = emptyString()
)
