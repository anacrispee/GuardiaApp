package com.example.guardia.di.modules

import com.example.guardia.features.feature_home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeScreenViewModel() }
}