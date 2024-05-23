package com.example.practicaltest.ui.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.practicaltest.R
import com.example.practicaltest.core.api.Post
import com.example.practicaltest.ui.components.ProductCard

@Composable
fun AllProductScreen(
    navController: NavController,
    viewModel: AllProductsScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1= Unit) {
        viewModel
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    AllProductsScreenContent(uiState = uiState)

}

@Composable
fun AllProductsScreenContent(
    uiState: ScreenState
){
    Scaffold(
        Modifier.fillMaxSize()
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(text = "Best Selling", style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier,
            ) {
                items(items = uiState.posts){ item ->
                    ProductCard(
                        image = R.drawable.login,
                        title = item.title,
                        price = item.body.take(10),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

        }
    }

}

val items = (1..10).map {
    Post(
        title = "Item 1",
        id = it,
        body = "Body ${it}",
        userId = it
    )
}

@Preview
@Composable
fun AllProductsScreenPreview(){
    AllProductsScreenContent(uiState = ScreenState(posts = items))
}