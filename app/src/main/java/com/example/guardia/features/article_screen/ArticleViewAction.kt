package com.example.guardia.features.article_screen

sealed class ArticleViewAction {
    data class UpdateIsLoading(val value: Boolean) : ArticleViewAction()
}