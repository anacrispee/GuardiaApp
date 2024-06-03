package com.example.guardia.features.feature_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class HomeViewModel() : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(HomeViewState())

    fun dispatcherViewAction(action: HomeViewAction) {
//        when (action) {
//        }
    }
}