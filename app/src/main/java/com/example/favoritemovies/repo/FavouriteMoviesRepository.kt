package com.example.favoritemovies.repo

import com.example.favoritemovies.model.FavouriteMovies
import com.example.favoritemovies.service.FavouriteMoviesService
import retrofit2.Response
import javax.inject.Inject

class FavouriteMoviesRepository @Inject constructor(
    private val favouriteMoviesService: FavouriteMoviesService
) {
    suspend fun getFavouriteMovies(): Response<FavouriteMovies> {
        return favouriteMoviesService.getFavouriteMovies()
    }
}