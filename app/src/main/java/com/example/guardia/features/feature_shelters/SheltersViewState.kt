package com.example.guardia.features.feature_shelters

import com.example.guardia.data_remote.model.shelter.SheltersListModel

data class SheltersViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val shelters: SheltersListModel = UtilFactory.DUMMY_SHELTER_LIST_MODEL,
    val isEmptyState: Boolean = false
)