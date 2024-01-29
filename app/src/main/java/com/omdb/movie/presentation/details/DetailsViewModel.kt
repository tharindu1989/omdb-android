package com.omdb.movie.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdb.movie.di.IoDispatcher
import com.omdb.movie.domain.movie.MovieDetails
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _movie = MutableStateFlow<MovieDetails?>(null)
    val movie = _movie.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun getDetails(id: String) {
        _isLoading.update { true }
        viewModelScope.launch(dispatcher) {
            val details = movieRepository.details(id)
            if (details is Response.Success) {
                _movie.update { details.data }
            }
            _isLoading.update { false }
        }
    }
}