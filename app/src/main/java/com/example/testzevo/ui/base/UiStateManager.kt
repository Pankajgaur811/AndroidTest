package com.example.testzevo.ui.base

sealed interface UiStateManager<out T> {

    data class Success<T>(val data: T) : UiStateManager<T>

    data class Error(val message: String) : UiStateManager<Nothing>

    object Loading : UiStateManager<Nothing>

}