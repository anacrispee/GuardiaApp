package com.example.guardia.features.feature_shelters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class SheltersViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(SheltersViewState())

    fun dispatcherViewAction(action: SheltersViewAction) {
        when (action) {
            is SheltersViewAction.SearchNewShelter -> searchNewShelter(
                shelterName = action.shelterName
            )
        }
    }

    private fun searchNewShelter(shelterName: String) {
        if (shelterName.trim().isBlank() || shelterName.trim().length <= 3) {
            viewState = viewState.copy(
                isEmptyState = true
            )
        } else {
            viewState = viewState.copy(
                isEmptyState = false
            )
        }
    }
}