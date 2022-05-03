package com.example.favoritemovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.favoritemovies.R
import com.example.favoritemovies.databinding.FragmentFavouriteMoviesByRankingBinding
import com.example.favoritemovies.viewmodel.FavouriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteMoviesByRankingFragment : Fragment() {
    private val viewModel: FavouriteMoviesViewModel by viewModels()
    private lateinit var binding: FragmentFavouriteMoviesByRankingBinding

    @Inject
    lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favourite_movies_by_ranking, container, false
        )

        binding.apply {
            viewModel = this@FavouriteMoviesByRankingFragment.viewModel
            lifecycleOwner = requireActivity()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel?.moviesByRanking()?.observe(viewLifecycleOwner) {
            favouriteMoviesAdapter.updateList(it)
            binding.recyclerView.adapter = favouriteMoviesAdapter
        }
    }
}