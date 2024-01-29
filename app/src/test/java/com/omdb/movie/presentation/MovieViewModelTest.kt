package com.omdb.movie.presentation

import com.omdb.movie.data.MovieRepositoryFake
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.presentation.movies.MoviesViewModel
import com.omdb.movie.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule();

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryFake()
    }

    @Test
    fun `Movie list should be loaded successfully`() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val moviesViewModel = MoviesViewModel(
            movieRepository = movieRepository,
            ioDispatcher = testDispatcher
        )
        moviesViewModel.onSearch("bat")
        advanceUntilIdle()
        assertEquals(10, moviesViewModel.movies.value.size)
    }

    @Test
    fun `Movie list should be empty when search text length less than 3`() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val moviesViewModel = MoviesViewModel(
            movieRepository = movieRepository,
            ioDispatcher = testDispatcher
        )
        moviesViewModel.onSearch("ba")
        advanceUntilIdle()
        assertEquals(0, moviesViewModel.movies.value.size)
    }
}