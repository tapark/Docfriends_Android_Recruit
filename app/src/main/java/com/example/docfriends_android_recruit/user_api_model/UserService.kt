package com.example.docfriends_android_recruit.user_api_model

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/v3/388e1a9b-94f0-4eaf-8510-a87a9bd9cc6b")
    fun getUserData(): Call<UserDto>
}