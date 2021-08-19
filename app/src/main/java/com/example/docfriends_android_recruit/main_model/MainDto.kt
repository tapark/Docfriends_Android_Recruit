package com.example.docfriends_android_recruit.main_model

import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName("pageMap") val pageMap: PageMapModel,
    @SerializedName("expertListPosition") val expertListPosition: Int,
    @SerializedName("companyListPosition") val companyListPosition: Int,
    @SerializedName("consultList") val consultList: List<ConsultModel>,
    @SerializedName("expertList") val expertList: List<ExpertModel>,
    @SerializedName("companyList") val companyList: List<CompanyModel>,

)
