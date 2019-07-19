package com.example.myapplication.WebService

import com.example.myapplication.model.Results
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("getJoke")
    fun getResponseBody() : Call<Results>

}