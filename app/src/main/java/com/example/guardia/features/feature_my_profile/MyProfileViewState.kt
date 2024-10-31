package com.example.guardia.features.feature_my_profile

import com.example.guardia.data_remote.model.user.UserModel

data class MyProfileViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val user: UserModel? = null
)

object DummyViewState {
    val DUMMY_PROFILE = UserModel(
        image = "https://www.perfocal.com/blog/content/images/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg",
        name = "Jurema dos Santos",
        email = "juremasantos97",
        phone = "+55 11 99999-9999",
        address = "88350-054 Rua das Flores Rosas, Vale - Brusque/SC"
    )
}