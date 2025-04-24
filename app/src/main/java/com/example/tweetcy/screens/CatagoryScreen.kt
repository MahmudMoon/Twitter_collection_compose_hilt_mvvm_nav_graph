package com.example.tweetcy.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tweetcy.models.TweetListItem
import com.example.tweetcy.viewmodels.CatagoryViewModel

private const val TAG = "CatagoryScreen"

@Composable
fun CategoryScreen(modifier: Modifier = Modifier, navController: NavController) {
    val categoryViewModel: CatagoryViewModel = hiltViewModel()

    val categories = categoryViewModel.categories.collectAsState(
        initial = emptyList()
    )

    if(categories.value.isEmpty()){
        Box(modifier = modifier.fillMaxSize(1.0f), contentAlignment = Alignment.Center) {
            Text(text = "Loading Categories", modifier = Modifier.padding(16.dp), color = Color.Red)
        }
    }else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value.distinct()) { category ->
                CategoryItem(categoryName = category) {
                    Log.d(TAG, "Category clicked: $category")
                    navController.navigate("TweetDetail/${category}")
                }
            }
        }
    }



}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String = "",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray)
            .background(Color.Red)
            .clickable {
                // Handle category click
                Log.d(TAG, "Category clicked: $categoryName")
                onClick()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(text = categoryName, modifier = Modifier.padding(0.dp, 8.dp), color = Color.White)
    }

}