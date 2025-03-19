package com.example.guardia.features.article_screen

data class ArticleViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null
)