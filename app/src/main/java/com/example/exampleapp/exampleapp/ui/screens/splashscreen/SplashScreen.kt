package com.example.exampleapp.exampleapp.ui.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.exampleapp.R
import com.example.exampleapp.exampleapp.base.BaseViewModel
import com.example.exampleapp.exampleapp.navigation.NavigationTree
import com.example.exampleapp.exampleapp.ui.theme.ExampleAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: BaseViewModel) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        viewModel.getCategory()
        delay(4000)
        navController.popBackStack()
        navController.navigate(NavigationTree.Root.Categories.name)
    }
    Splash(scale = scale)
}

@Composable
fun Splash(scale: Animatable<Float, AnimationVector1D>) {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_local_pizza_24),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .scale(scale.value)
    )
}


@Preview(showBackground = true)
@Composable
fun PrevSplashScreen() {
    val scale = remember {
        Animatable(0f)
    }
    ExampleAppTheme {
        Splash(scale = scale)
    }
}