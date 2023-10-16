package com.example.testzevo.data.repositories

import com.example.testzevo.data.api.ApiServices
import com.example.testzevo.data.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopNewsHeadLinesRepository @Inject constructor(private val apiService: ApiServices) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(apiService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

}