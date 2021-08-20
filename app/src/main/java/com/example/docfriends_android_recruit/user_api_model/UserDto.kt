package com.example.docfriends_android_recruit.user_api_model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("spring") val spring: String,
    @SerializedName("summer") val summer: String,
    @SerializedName("fall") val fall: String,
    @SerializedName("winter") val winter: String,
    @SerializedName("users") val users: List<UserModel>
)