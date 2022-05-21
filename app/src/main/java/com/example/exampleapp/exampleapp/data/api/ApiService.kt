package com.example.exampleapp.exampleapp.data.api

import com.example.exampleapp.exampleapp.data.model.AllCategories
import com.example.exampleapp.exampleapp.data.model.MealsByCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<AllCategories>

    @GET("/api/json/v1/1/filter.php")
    suspend fun getFilterByCategory(@Query("c") category: String): Response<MealsByCategory>
}

