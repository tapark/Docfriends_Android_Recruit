package com.example.docfriends_android_recruit.user_api_model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("auth") val auth: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile") val profile: String
)