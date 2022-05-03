package com.example.favoritemovies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritemovies.databinding.MovieListItemBinding
import com.example.favoritemovies.model.Movie
import javax.inject.Inject

class FavouriteMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavouriteMoviesAdapter.ViewHolder>() {
    private var movieList = mutableListOf<Movie>()

    fun updateList(list: MutableList<Movie>) {
        this.movieList = list
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteMoviesAdapter.ViewHolder =
        ViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movie = item
        }
    }

}