package com.example.docfriends_android_recruit.main_api_model

import com.google.gson.annotations.SerializedName

data class ExpertModel(
    @SerializedName("name") val name:String,
    @SerializedName("typeName") val typeName:String,
    @SerializedName("companyName") val companyName:String,
    @SerializedName("profileImagePath") val profileImagePath:String,
    @SerializedName("tagList") val tagList:List<TagModel>
)
