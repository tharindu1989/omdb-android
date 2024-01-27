package com.omdb.movie.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    init {
        searchByTitle("bat")
    }

    fun searchByTitle(title: String) {
        viewModelScope.launch(Dispatchers.Default) {
            when (val result = movieRepository.searchMovies(title = title)) {
                is Response.Success -> {
                    Log.e("SUCCESS", result.data?.total.toString())
                }

                is Response.Error -> {
                    Log.e("ERROR", result.message.toString())
                }
            }
        }
    }

}