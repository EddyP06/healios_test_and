package com.eddy.healiostest.data.models.remote

import com.eddy.healiostest.ui.extension.emptyString
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("title")
    val title: String = emptyString(),
    @SerializedName("body")
    val body: String = emptyString()
)
