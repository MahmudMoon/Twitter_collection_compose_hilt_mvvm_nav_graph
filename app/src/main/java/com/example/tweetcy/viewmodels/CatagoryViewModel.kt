package com.example.tweetcy.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetcy.repos.TwitterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CatagoryViewModel"

@HiltViewModel
class CatagoryViewModel @Inject constructor(private val twitterRepo: TwitterRepo) : ViewModel() {
    val categories: StateFlow<List<String>>
    get() = twitterRepo.categories

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "CatagoryViewModel: init: Called")
            twitterRepo.getAllCategories()
        }
    }
}