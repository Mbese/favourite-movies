package com.example.favoritemovies.model

import com.google.gson.annotations.SerializedName


data class FavouriteMovies(
    @SerializedName("type") var type: String? = null,
    @SerializedName("components") var components: ArrayList<Components> = arrayListOf()
)