package com.example.guardia.di.modules

import com.example.guardia.features.authentication.create_account.CreateAccountViewModel
import com.example.guardia.features.authentication.login.LoginViewModel
import com.example.guardia.features.feature_home.HomeViewModel
import com.example.guardia.features.feature_my_profile.MyProfileViewModel
import com.example.guardia.features.feature_panic_button.PanicButtonViewModel
import com.example.guardia.features.feature_shelters.SheltersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { PanicButtonViewModel() }
    viewModel { SheltersViewModel() }
    viewModel { MyProfileViewModel() }
    viewModel { LoginViewModel() }
    viewModel { CreateAccountViewModel() }
}