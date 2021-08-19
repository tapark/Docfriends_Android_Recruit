package com.example.docfriends_android_recruit.main_model

import com.google.gson.annotations.SerializedName

data class CompanyModel(
    @SerializedName("companyName") val companyName: String,
    @SerializedName("address") val address: String,
    @SerializedName("addressEtc") val addressEtc: String,
    @SerializedName("introPath") val introPath: String
)
