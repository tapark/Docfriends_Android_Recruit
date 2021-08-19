package com.example.docfriends_android_recruit.api_model

import com.google.gson.annotations.SerializedName

data class PageMapModel(
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("rowPerPage") val rowPerPage: Int,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("totalCount") val totalCount: Int
)
