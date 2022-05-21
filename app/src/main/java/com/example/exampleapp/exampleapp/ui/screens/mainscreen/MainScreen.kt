package com.example.exampleapp.exampleapp.ui.screens.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.exampleapp.R
import com.example.exampleapp.exampleapp.base.BaseViewModel
import com.example.exampleapp.exampleapp.data.model.Meal
import com.example.exampleapp.exampleapp.data.model.MealsByCategory
import com.example.exampleapp.exampleapp.ui.theme.ExampleAppTheme

@Composable
fun MainScreen(viewModel: BaseViewModel) {
    val category = viewModel.mealByCategory.observeAsState(MealsByCategory(listOf())).value.meals

    Scaffold {
        LazyColumn {
            items(category) { category ->
                MealsItem(category = category)
            }
        }
    }
}

@Composable
fun MealsItem(category: Meal) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = category.strMealThumb,
                placeholder = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Text(
                text = category.strMeal,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevMealsItem() {
    ExampleAppTheme {
        MealsItem(category = Meal("", "", ""))
    }
}