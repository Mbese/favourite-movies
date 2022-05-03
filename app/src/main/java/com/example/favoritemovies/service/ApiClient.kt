package com.example.favoritemovies.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        const val BASE_URL = "https://beta-now.dstv.com/"
        private fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
        }

        fun getFavouriteMoviesService(): FavouriteMoviesService {
            return getRetrofitInstance().build().create(FavouriteMoviesService::class.java)
        }
    }
}