package com.example.docfriends_android_recruit.main_api_model

import com.example.docfriends_android_recruit.home.DEFAULT_VIEW_TYPE
import com.google.gson.annotations.SerializedName

data class ConsultModel(
    val viewType: Int = DEFAULT_VIEW_TYPE,
    val otherData: Any,
    @SerializedName("seq") val seq: Int = -1,
    @SerializedName("title") val title: String = "",
    @SerializedName("context") val context: String = "",
    @SerializedName("regDate") val regDate: Long = 0L,
    @SerializedName("answerCnt") val answerCnt: Int = 0,
    @SerializedName("tagList") val tagList: List<TagModel> = emptyList()
)
