package com.example.tweetcy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetcy.repos.TwitterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatagoryViewModel @Inject constructor(private val twitterRepo: TwitterRepo) : ViewModel() {
    val categories: StateFlow<List<String>>
    get() = twitterRepo.categories

    init {
        viewModelScope.launch(Dispatchers.IO) {
            twitterRepo.getAllCategories()
        }
    }
}