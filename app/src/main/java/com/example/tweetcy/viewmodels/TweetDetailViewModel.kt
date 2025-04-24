package com.example.tweetcy.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetcy.models.TweetListItem
import com.example.tweetcy.repos.TwitterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "TweetDetailViewModel"

@HiltViewModel
class TweetDetailViewModel @Inject constructor(private val twitterRepo: TwitterRepo, savedStateHandle: SavedStateHandle) : ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = twitterRepo.tweets
    private val category = savedStateHandle.get<String>("category") ?: ""
    fun getAllTweetsAccordingToCategory() {
        Log.d(TAG, "getAllTweetsAccordingToCategory: Called for $category")
        viewModelScope.launch {
            Log.d(TAG, "getAllTweetsAccordingToCategory: $category")
            twitterRepo.getAllTweetsAccordingToCategory(category)
        }
    }
}