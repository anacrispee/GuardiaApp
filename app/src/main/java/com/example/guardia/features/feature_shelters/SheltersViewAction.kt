package com.example.guardia.features.feature_shelters

import android.content.Context

sealed class SheltersViewAction {
    data class GetContext(val context: Context) : SheltersViewAction()
    data class SearchNewShelter(val shelterName: String) : SheltersViewAction()
    data class OnCallClick(val phone: String) : SheltersViewAction()
}