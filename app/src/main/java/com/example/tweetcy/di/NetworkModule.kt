package com.example.tweetcy.di

import androidx.compose.ui.tooling.preview.Preview
import com.example.tweetcy.api.TwittesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTwittesApi(retrofit: Retrofit): TwittesApi {
        return retrofit.create(TwittesApi::class.java)
    }
}