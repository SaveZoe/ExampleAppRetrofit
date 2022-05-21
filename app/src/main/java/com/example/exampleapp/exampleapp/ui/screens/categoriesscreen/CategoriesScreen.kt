package com.example.exampleapp.exampleapp.ui.screens.categoriesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.exampleapp.R
import com.example.exampleapp.exampleapp.data.model.AllCategories
import com.example.exampleapp.exampleapp.data.model.Category
import com.example.exampleapp.exampleapp.navigation.NavigationTree
import com.example.exampleapp.exampleapp.base.BaseViewModel

@Composable
fun CategoriesScreen(viewModel: BaseViewModel, navController: NavController) {
    val category =
        viewModel.allCategory.observeAsState(initial = AllCategories(listOf())).value.categories
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(category) { item ->
                CategoryItem(item, navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CategoryItem(item: Category, navController: NavController, viewModel: BaseViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.DarkGray)
            .padding(bottom = 16.dp)
            .clickable {
                navController.navigate(NavigationTree.Root.Main.name + "/${item.strCategory}")
                viewModel.getFilterMeal(item.strCategory)
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = item.strCategoryThumb,
                placeholder = painterResource(R.drawable.img),
                contentDescription = null,
                modifier = Modifier.size(88.dp)
            )
            Text(
                text = item.strCategory,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}