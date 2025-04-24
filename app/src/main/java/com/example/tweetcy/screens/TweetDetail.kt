package com.example.tweetcy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tweetcy.models.TweetListItem
import com.example.tweetcy.viewmodels.TweetDetailViewModel

@Composable
fun TweetDetailScreen(modifier: Modifier = Modifier, navController: NavController) {
    val tweetDetailViewModel: TweetDetailViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        tweetDetailViewModel.getAllTweetsAccordingToCategory()
    }

    val tweetList = tweetDetailViewModel.tweets.collectAsState(
        initial = emptyList()
    )

    if (tweetList.value.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(1.0f), contentAlignment = Alignment.Center) {
            Text(text = "Loading Tweets", modifier = Modifier.padding(16.dp), color = Color.Red)
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(1.0f)
                .padding(0.dp, 8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(tweetList.value) { tweet ->
                TweetItem(tweet = tweet)
            }
        }
    }

}

@Composable
fun TweetItem(tweet: TweetListItem, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.padding(8.dp, 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .padding(4.dp)
        ) {
            // Display tweet details here
            Column {
                Text(text = tweet.tweet)
                Text(text = tweet.category, fontWeight = FontWeight.Bold)
            }
        }
    }
}
