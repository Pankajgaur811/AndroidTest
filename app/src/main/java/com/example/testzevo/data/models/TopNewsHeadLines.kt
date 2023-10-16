package com.example.testzevo.data.models

data class TopNewsHeadLines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)