package com.example.exampleapp.exampleapp.data.api

import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCategories() = apiService.getCategories()
    suspend fun getFilterMeals(filter: String) = apiService.getFilterByCategory(category = filter)

}