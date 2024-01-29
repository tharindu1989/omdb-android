package com.omdb.movie.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdb.movie.di.IoDispatcher
import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieData
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _movies = MutableStateFlow(listOf<MovieData>());
    val movies = _movies.asStateFlow()

    init {
        _searchText
            .debounce(1000)
            .distinctUntilChanged()
            .flowOn(ioDispatcher)
            .onEach { title ->
                if (title.length >= 3) {
                    searchByTitle(title)
                } else {
                    _movies.update { listOf() }
                }

            }
            .launchIn(viewModelScope)
    }

    fun onSearch(title: String) {
        _searchText.value = title
    }

    private fun searchByTitle(title: String) {
        _isSearching.update { true }
        viewModelScope.launch(ioDispatcher) {
            when (val result = movieRepository.searchMovies(title = title, MediaType.MOVIE)) {
                is Response.Success -> {
                    _movies.update { result.data.movies }
                }

                is Response.Error -> {
                    // TODO Show error message
                }
            }
            _isSearching.update { false }
        }
    }

}