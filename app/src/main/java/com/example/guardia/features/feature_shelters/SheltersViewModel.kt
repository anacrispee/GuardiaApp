package com.example.guardia.features.feature_shelters

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class SheltersViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(SheltersViewState())

    fun dispatcherViewAction(action: SheltersViewAction) {
        when (action) {
            is SheltersViewAction.GetContext -> viewState = viewState.copy(
                context = action.context
            )
            is SheltersViewAction.SearchNewShelter -> searchNewShelter(
                shelterName = action.shelterName
            )
            is SheltersViewAction.OnCallClick -> onCallClick(
                phone = action.phone
            )
        }
    }

    private fun onCallClick(phone: String) {
        val callIntent = "tel:$phone"
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(callIntent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        viewState.context?.startActivity(intent)
    }

    private fun searchNewShelter(shelterName: String) {
        viewState = if (shelterName.trim().isBlank() || shelterName.trim().length <= 3) {
            viewState.copy(
                isEmptyState = true
            )
        } else {
            viewState.copy(
                isEmptyState = false
            )
        }
    }
}