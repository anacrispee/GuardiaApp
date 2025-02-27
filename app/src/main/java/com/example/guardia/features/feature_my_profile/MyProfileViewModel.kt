package com.example.guardia.features.feature_my_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.guardia.data_remote.model.user.UserModel
import com.example.guardia.di.NavGraphConstants.LOGIN_SCREEN
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.core.component.KoinComponent

class MyProfileViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(MyProfileViewState())
    private val auth = Firebase.auth

    fun dispatcherViewAction(action: MyProfileViewAction) {
        when (action) {
            is MyProfileViewAction.Logout -> logout(
                navController = action.navController
            )
            is MyProfileViewAction.DeleteAccount -> deleteAccount(
                navController = action.navController
            )
            MyProfileViewAction.GetUser -> getUser()
        }
    }

    private fun getUser() {
        viewState = viewState.copy(
            isLoading = true
        )
        val currentUserModel = auth.currentUser
        currentUserModel?.let {
            viewState = viewState.copy(
                isLoading = false,
                user = UserModel(
                    image = currentUserModel.photoUrl.toString(),
                    name = currentUserModel.displayName.orEmpty(),
                    email = currentUserModel.email.orEmpty(),
                    phone = currentUserModel.phoneNumber.orEmpty()
                )
            )
        }
    }

    private fun logout(
        navController: NavHostController
    ) {
        auth.signOut()
        navController.navigate(LOGIN_SCREEN)
    }

    private fun deleteAccount(
        navController: NavHostController
    ) {
        auth.currentUser?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(LOGIN_SCREEN)
                }
            }
    }
}