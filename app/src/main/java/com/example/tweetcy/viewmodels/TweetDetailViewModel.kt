package com.example.tweetcy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetcy.models.TweetListItem
import com.example.tweetcy.repos.TwitterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetDetailViewModel @Inject constructor(private val twitterRepo: TwitterRepo) : ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = twitterRepo.tweets

    fun getAllTweetsAccordingToCategory(category: String) {
        viewModelScope.launch {
            twitterRepo.getAllTweetsAccordingToCategory(category)
        }
    }
}