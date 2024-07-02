package com.example.guardia.features.feature_shelters

import com.example.guardia.data_remote.model.shelter.ShelterListModel
import com.example.guardia.data_remote.model.shelter.ShelterModel

data class SheltersViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val shelters: ShelterListModel? = null,
    val isEmptyState: Boolean = false
)