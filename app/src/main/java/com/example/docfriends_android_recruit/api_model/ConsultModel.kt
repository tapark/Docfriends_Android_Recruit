package com.example.docfriends_android_recruit.api_model

import com.google.gson.annotations.SerializedName

data class ConsultModel(
    @SerializedName("seq") val seq: Int,
    @SerializedName("title") val title: String,
    @SerializedName("context") val context: String,
    @SerializedName("regDate") val regDate: Long,
    @SerializedName("answerCnt") val answerCnt: Int,
    @SerializedName("tagList") val tagList: List<TagModel>
)
