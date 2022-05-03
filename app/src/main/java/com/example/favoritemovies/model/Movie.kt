package com.example.favoritemovies.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("label") var label: String? = null,
    @SerializedName("valueToOrderBy") var valueToOrderBy: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("rank") var rank: Int? = null,
    @SerializedName("synopsis") var synopsis: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("releaseDate") var releaseDate: Int? = null
)