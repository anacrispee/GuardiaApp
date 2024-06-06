package com.example.guardia.features.feature_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardia.domain.use_case.GetWomansViolenceArticlesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel : ViewModel(), KoinComponent {

    var viewState by mutableStateOf(HomeViewState())

    private val getWomansViolenceArticlesUseCase: GetWomansViolenceArticlesUseCase by inject {
        parametersOf(viewModelScope)
    }

    fun dispatcherViewAction(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.ChangeInputValue -> {
                viewState = viewState.copy(
                    searchInputValue = action.value
                )
            }

            HomeViewAction.GetArticles -> GetArticles()
        }
    }

    private fun GetArticles() {
        viewState = viewState.copy(
            isLoading = true
        )
        getWomansViolenceArticlesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    isLoading = false,
                    violenceArticles = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it
                )
            }
        )
    }
}