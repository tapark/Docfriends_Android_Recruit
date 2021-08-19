package com.example.docfriends_android_recruit.api_model

import com.google.gson.annotations.SerializedName

data class TagModel(
    @SerializedName("key") val key: Int,
    @SerializedName("name") val name: String
)
