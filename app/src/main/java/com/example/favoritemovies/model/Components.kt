package com.example.favoritemovies.model

import com.google.gson.annotations.SerializedName

data class Components(
    @SerializedName("type") var type: String? = null,
    @SerializedName("items") var movie: ArrayList<Movie> = arrayListOf()
)