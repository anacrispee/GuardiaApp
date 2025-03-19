package com.example.guardia.features.article_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class ArticleViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(ArticleViewState())

    fun dispatcherViewAction(action: ArticleViewAction) {
        when (action) {
            is ArticleViewAction.UpdateIsLoading -> viewState = viewState.copy(
                isLoading = action.value
            )
        }
    }
}