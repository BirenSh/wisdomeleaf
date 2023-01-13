package com.example.wishdomassignment

import com.example.wishdomassignment.models.ModelData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("list?page=2&limit=20")
    fun getApiData(): Call<ModelData?>?
}