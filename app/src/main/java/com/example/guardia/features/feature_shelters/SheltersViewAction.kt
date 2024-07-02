package com.example.guardia.features.feature_shelters

sealed class SheltersViewAction {
    data class SearchNewShelter(val shelterName: String) : SheltersViewAction()
}