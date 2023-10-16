package com.example.testzevo.ui.view.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testzevo.data.models.Article
import com.example.testzevo.data.repositories.TopNewsHeadLinesRepository
import com.example.testzevo.ui.base.UiStateManager
import com.example.testzevo.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopNewsHeadlinesViewModel(private val topNewsHeadlineRepository: TopNewsHeadLinesRepository) : ViewModel() {

        private val _uiStateManager = MutableStateFlow<UiStateManager<List<Article>>>(UiStateManager.Loading)

        val uiState: StateFlow<UiStateManager<List<Article>>> = _uiStateManager

        init {
            fetchTopHeadlines()
        }

        private fun fetchTopHeadlines() {
            viewModelScope.launch {
                topNewsHeadlineRepository.getTopHeadlines(COUNTRY)
                    .catch { e ->
                        _uiStateManager.value = UiStateManager.Error(e.toString())
                    }
                    .collect {
                        _uiStateManager.value = UiStateManager.Success(it)
                    }
            }
        }

    }