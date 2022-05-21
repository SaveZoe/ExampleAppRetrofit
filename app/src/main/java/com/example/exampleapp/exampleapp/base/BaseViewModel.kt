package com.example.exampleapp.exampleapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleapp.exampleapp.data.api.ApiRepository
import com.example.exampleapp.exampleapp.data.model.AllCategories
import com.example.exampleapp.exampleapp.data.model.MealsByCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BaseViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _allCategory = MutableLiveData<AllCategories>()
    val allCategory: LiveData<AllCategories> get() = _allCategory

    private val _mealByCategory = MutableLiveData<MealsByCategory>()
    val mealByCategory: LiveData<MealsByCategory> get() = _mealByCategory

    fun getCategory() {
        viewModelScope.launch {
            repository.getCategories().let {
                if (it.isSuccessful) {
                    _allCategory.postValue(it.body())
                } else {
                    Log.d("MyTag", "getCategory: ${it.errorBody()}")
                }
            }
        }
    }

    fun getFilterMeal(filterName: String) {
        viewModelScope.launch {
            repository.getFilterMeals(filter = filterName).let {
                if (it.isSuccessful) {
                    _mealByCategory.postValue(it.body())
                } else {
                    Log.d("MyTag", "getCategory: ${it.errorBody()}")
                }
            }
        }
    }

}