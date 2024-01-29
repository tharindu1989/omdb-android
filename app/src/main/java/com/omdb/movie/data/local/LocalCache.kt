package com.omdb.movie.data.local

import com.omdb.movie.data.local.movie.MovieLocal

// TODO move to room database
class LocalCache {
    private val cacheMap: MutableMap<String, List<MovieLocal>> = mutableMapOf()

    fun saveResult(searchWord: String, result: List<MovieLocal>) {
        cacheMap[searchWord] = result
    }

    fun searchResult(searchWord: String): List<MovieLocal> {
        return cacheMap[searchWord] ?: listOf()
    }

    fun hasCache(searchWord: String): Boolean {
        return cacheMap.containsKey(searchWord)
    }
    // TODO expire cache
    fun clearCache() {
        cacheMap.clear()
    }
}