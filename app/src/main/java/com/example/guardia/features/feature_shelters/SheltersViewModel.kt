package com.example.guardia.features.feature_shelters

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.guardia.R
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

            is SheltersViewAction.OnFindShelter -> onFindShelter(
                latitudeLongitude = action.latitudeLongitude
            )
        }
    }

    private fun onFindShelter(
        latitudeLongitude: Pair<Double?, Double?>
    ) {
        try {
            val title: String = viewState.context?.getString(R.string.app_name).orEmpty()
            val latitude = latitudeLongitude.first
            val longitude = latitudeLongitude.second

            val uriWaze = "waze://?ll=$latitude, $longitude&navigate=yes"
            val intentWaze = Intent(Intent.ACTION_VIEW, Uri.parse(uriWaze))
                .setPackage("com.waze")

            val uriGoogle = "google.navigation:q=$latitude,$longitude"
            val intentGoogleNav = Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle))
                .setPackage("com.google.android.apps.maps")

            val chooserIntent = Intent.createChooser(intentGoogleNav, title)
            val extraIntents = arrayOfNulls<Intent>(1)
            extraIntents[0] = intentWaze
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents)
            viewState.context?.startActivity(chooserIntent)
        } catch (e: Exception) {
            Toast.makeText(
                viewState.context,
                viewState.context?.getString(R.string.cannot_open_maps_app),
                Toast.LENGTH_SHORT
            ).show()
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
        if (shelterName.trim().isBlank() && shelterName.trim().length <= 3) {
            viewState = viewState.copy(
                isEmptyState = true
            )
        }
    }
}