package com.example.favoritemovies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.favoritemovies.repo.FavouriteMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavouriteMoviesViewModelTest {

    private lateinit var viewModel: FavouriteMoviesViewModel
    private lateinit var repo : FavouriteMoviesRepository
    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        repo = mock(FavouriteMoviesRepository::class.java)
        viewModel = FavouriteMoviesViewModel(repo)
    }

    @Test
    fun checkLoadingState_OnRequestInit_isTrue(){
        viewModel._showProgressBar.value = true
        assertTrue(viewModel._showProgressBar.value!!)
    }


    @Test
    fun checkLoadingState_OnRequestComplete_isFalse(){
        viewModel._showProgressBar.value = false
        assertFalse(viewModel._showProgressBar.value!!)
    }

    @Test
    fun testViewModelCallToTheRepoWithSuccess() {
        viewModel.getFavouriteMovies()
        assertTrue(viewModel._movies.value != null)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}