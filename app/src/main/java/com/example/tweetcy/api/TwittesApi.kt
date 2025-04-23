package com.example.tweetcy.api

import com.example.tweetcy.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TwittesApi {

    @GET("b/68087ddb8561e97a500585b4?meta=false")
    suspend fun getAllTweetsAccordingToCatagory(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("b/68087ddb8561e97a500585b4?meta=false")
    @Headers("X-JSON-Path: tweets[*].category")
    suspend fun getAllCategories(): Response<List<String>>
}
