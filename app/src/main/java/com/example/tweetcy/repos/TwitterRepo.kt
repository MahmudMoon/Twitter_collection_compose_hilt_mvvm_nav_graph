package com.example.tweetcy.repos

import android.util.Log
import com.example.tweetcy.api.TwittesApi
import com.example.tweetcy.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val TAG = "TwitterRepo"

class TwitterRepo @Inject constructor(private val twitterApi: TwittesApi) {

    private val _categories = MutableStateFlow(listOf<String>())
    val categories: StateFlow<List<String>>
    get() = _categories

    private val _tweets = MutableStateFlow(listOf<TweetListItem>())
    val tweets: StateFlow<List<TweetListItem>>
    get() = _tweets

    suspend fun getAllTweetsAccordingToCategory(category: String) {
        twitterApi.getAllTweetsAccordingToCatagory("tweets[?(@.category==\"${category}\")]").let {
            if (it.isSuccessful) {
                it.body()?.let { tweetsList ->
                    if (tweetsList.isNotEmpty()) {
                        Log.d(TAG, "getAllTweetsAccordingToCategory: $tweetsList")
                        _tweets.emit(tweetsList)
                    } else {
                        Log.e(TAG, "getAllTweetsAccordingToCategory: No tweets found")
                    }
                }
            } else {
                Log.e(TAG, "getAllTweetsAccordingToCategory: " + it.errorBody()?.toString())
            }
        }
    }

    suspend fun getAllCategories() {
        twitterApi.getAllCategories().also {
            if (it.isSuccessful) {
                it.body()?.let { categoriesList ->
                    if (categoriesList.isNotEmpty())
                        _categories.emit(categoriesList)
                    else
                        Log.e(TAG, "getAllCategories: No categories found")
                }
            } else {
                Log.e(TAG, "getAllCategories: " + it.errorBody()?.toString())
            }
        }
    }

}