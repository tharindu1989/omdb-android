package com.omdb.movie.presentation

import com.omdb.movie.data.MovieRepositoryFake
import com.omdb.movie.presentation.details.DetailsViewModel
import com.omdb.movie.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var movieRepository: MovieRepositoryFake

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryFake()
    }

    @Test
    fun `Movie details should be loaded successfully`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val detailsViewModel = DetailsViewModel(
            movieRepository = movieRepository,
            dispatcher = dispatcher
        )

        detailsViewModel.getDetails("tes-id")

        assertEquals("Movie Title", detailsViewModel.movie.value?.title)
        assertEquals(false, detailsViewModel.isLoading.value)
    }

    @Test
    fun `Movie details should not be loaded when incorrect id`() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        movieRepository.error = Exception("Not Found")

        val detailsViewModel = DetailsViewModel(
            movieRepository = movieRepository,
            dispatcher = dispatcher
        )

        detailsViewModel.getDetails("")

        assertEquals(null, detailsViewModel.movie.value?.title)
        assertEquals(false, detailsViewModel.isLoading.value)
    }
}