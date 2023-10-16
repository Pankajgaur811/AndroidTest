package com.example.testzevo.data.api

import com.example.testzevo.data.models.TopNewsHeadLines
import com.example.testzevo.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiServices {
    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopNewsHeadLines

}