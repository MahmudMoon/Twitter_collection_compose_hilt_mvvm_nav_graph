package com.example.tweetcy.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweetcy.models.TweetListItem
import com.example.tweetcy.viewmodels.TweetDetailViewModel

@Composable
fun TweetDetailScreen(modifier: Modifier = Modifier, category: String = "") {
    val tweetDetailViewModel: TweetDetailViewModel = viewModel()
    tweetDetailViewModel.getAllTweetsAccordingToCategory(category)
    tweetDetailViewModel.tweets.collectAsState(
        initial = emptyList()
    ).value.forEach { tweet ->
        LazyColumn {
            items(tweetDetailViewModel.tweets.value) { tweet ->
                TweetItem(tweet = tweet)
            }
        }
    }

}

@Composable
fun TweetItem(tweet: TweetListItem) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
    ) {
        // Display tweet details here
        Column {
            Text(text = tweet.tweet)
            Text(text = tweet.category)
        }
    }
}
