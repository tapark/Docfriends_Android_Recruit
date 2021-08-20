package com.example.docfriends_android_recruit.main_api_model

import retrofit2.Call
import retrofit2.http.GET

interface MainService {
    @GET("/Docfriends_Android_Recruit/api/home.json")
    fun getMainData(): Call<MainDto>
}