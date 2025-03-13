package com.example.guardia.di.modules

import com.example.guardia.AppConstants.NEWS_API_BASE_URL
import com.example.guardia.data.remote.firebase.authentication.AuthenticationDataSource
import com.example.guardia.data.remote.news_api.NewsApiRemoteDataSource
import com.example.guardia.data_remote.datasource.AuthenticationDataSourceImpl
import com.example.guardia.data_remote.datasource.NewsApiRemoteDataSourceImpl
import com.example.guardia.data_remote.factory.WebServiceFactory
import com.example.guardia.data_remote.services.NewsApiService
import com.example.guardia.data_remote.utils.RequestWrapper
import com.example.guardia.data_remote.utils.RequestWrapperImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteValue = module {

    single {
        WebServiceFactory.provideOkHttpClient()
    }

    single {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = NEWS_API_BASE_URL,
        ) as NewsApiService
    }

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single<NewsApiRemoteDataSource> { NewsApiRemoteDataSourceImpl(get()) }

    single<AuthenticationDataSource> { AuthenticationDataSourceImpl(
        FirebaseAuth.getInstance()
    ) }
}