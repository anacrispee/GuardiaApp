package com.example.guardia.features.feature_my_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class MyProfileViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(MyProfileViewState())

    fun dispatcherViewAction(action: MyProfileViewAction) {
        when (action) {
            MyProfileViewAction.GetUser -> getUser()
        }
    }

    private fun getUser() {
        viewState = viewState.copy(
            user = DummyViewState.DUMMY_PROFILE
        )
    }
}