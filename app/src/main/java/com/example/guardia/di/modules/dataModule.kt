package com.example.guardia.di.modules

import com.example.guardia.data.repository.AuthenticationRepositoryImpl
import com.example.guardia.data.repository.NewsApiRemoteRepositoryImpl
import com.example.guardia.domain.repository.AuthenticationRepository
import com.example.guardia.domain.repository.NewsApiRemoteRepository
import org.koin.dsl.module

val dataModule = module {
    single<NewsApiRemoteRepository> { NewsApiRemoteRepositoryImpl(get()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}