package com.example.practicaltest.ui.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.practicaltest.core.api.Post
import com.example.practicaltest.ui.components.GreenButton
import com.example.practicaltest.ui.components.ProductCard

@Composable
fun ProductScreen(
    id: String,
    navController: NavController,
    viewModel: ProductScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = Unit) {
        viewModel.getProductById()
    }
    ProductScreenContent(uiState = uiState, id = id)

}

@Composable
fun ProductScreenContent(uiState: ProductScreenState, id: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            uiState.product?.let { post ->
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    IconButton(onClick = { /* Handle click event here */ }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )

                    }

                    Text(
                        text = post.title,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )


                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.Black)

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = "https://picsum.photos/200/30${post.id}",
                        contentDescription = "items",
                        modifier = Modifier
                            .width(400.dp)
                            .height(400.dp)
                            .padding(top = 16.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Text(
                            text = post.title,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Text(
                            text = "600 KES",
                            style = TextStyle(
                                color = Color(0xFF013014),
                                fontSize = 20.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Text(
                            text = "Apparel",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                    ) {
                        Text(
                            text = post.body,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 17.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    GreenButton(
                        text = "Add To Cart", onClick = {

                        })

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp)
                    ) {
                        ProductCard(
                            image = "https://picsum.photos/200/30${post.id}",
                            title = post.title,
                            price = post.body.take(10),
                            modifier = Modifier.padding(4.dp),
                            onClick = {

                            }
                        )

                    }


                }
            }

        }
    }

}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductScreenContent(
        uiState = ProductScreenState(
            product = Post(
                body = "1234",
                id = 1,
                title = "33333",
                userId = 1
            )
        ), id = "1"
    )
}