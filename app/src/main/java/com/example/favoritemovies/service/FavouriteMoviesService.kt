package com.example.favoritemovies.service

import com.example.favoritemovies.model.FavouriteMovies
import retrofit2.Response
import retrofit2.http.GET

interface FavouriteMoviesService {
    @GET("top5MoviesAssessement.json")
    suspend fun getFavouriteMovies(): Response<FavouriteMovies>
}