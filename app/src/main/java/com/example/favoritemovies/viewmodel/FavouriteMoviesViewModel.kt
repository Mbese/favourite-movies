package com.example.favoritemovies.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoritemovies.model.Components
import com.example.favoritemovies.model.Movie
import com.example.favoritemovies.repo.FavouriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavouriteMoviesViewModel @Inject constructor(
    private val repo: FavouriteMoviesRepository
) : ViewModel() {

    private val _moviesByReleaseDate = MutableLiveData<MutableList<Movie>>()
    fun moviesByReleaseDate(): LiveData<MutableList<Movie>> = _moviesByReleaseDate

    private val _moviesByRanking = MutableLiveData<MutableList<Movie>>()
    fun moviesByRanking(): LiveData<MutableList<Movie>> = _moviesByRanking

    val _movies = MutableLiveData<Components>()
    private fun movies(): LiveData<Components> = _movies

    val _showProgressBar = MutableLiveData(false)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    init {
        getFavouriteMovies()
    }

    @VisibleForTesting
    fun getFavouriteMovies() {
        _showProgressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getFavouriteMovies()
            withContext(Dispatchers.Main) {
                _showProgressBar.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _movies.value = responseBody.components.first { it.type == "movie-list" }
                    sortMoviesByRanking()
                    sortMoviesByReleaseDate()
                }
            }
        }
    }

    private fun sortMoviesByRanking() {
        _moviesByRanking.value = movies().value?.movie
        _moviesByRanking.value?.sortBy { it.rank }
    }

    private fun sortMoviesByReleaseDate() {
        _moviesByReleaseDate.value = movies().value?.movie?.toMutableList()
        _moviesByReleaseDate.value?.sortBy { it.releaseDate }
    }
}