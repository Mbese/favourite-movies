package com.example.favoritemovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.favoritemovies.R
import com.example.favoritemovies.databinding.FragmentFavouriteMovieByReleaseDateBinding
import com.example.favoritemovies.viewmodel.FavouriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteMovieByReleaseDateFragment : Fragment() {
    private val viewModel: FavouriteMoviesViewModel by viewModels()
    private lateinit var binding: FragmentFavouriteMovieByReleaseDateBinding

    @Inject
    lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favourite_movie_by_release_date, container, false
        )

        binding.apply {
            viewModel = this@FavouriteMovieByReleaseDateFragment.viewModel
            lifecycleOwner = requireActivity()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel?.moviesByReleaseDate()?.observe(viewLifecycleOwner) {
            favouriteMoviesAdapter.updateList(it)
            binding.recyclerView.adapter = favouriteMoviesAdapter
        }
    }
}