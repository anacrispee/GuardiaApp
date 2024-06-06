package com.example.guardia.data_remote.datasource

import com.example.guardia.data.remote.news_api.NewsApiRemoteDataSource
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.data_remote.services.NewsApiService
import com.example.guardia.data_remote.utils.RequestWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsApiRemoteDataSourceImpl(
    private val webService: NewsApiService,
) : NewsApiRemoteDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getDomesticViolenceArticles(): Flow<List<DomesticViolenceArticleResponse>> = flow {
        emit(
            requestWrapper.wrapperGenericResponse {
                webService.getDomesticViolenceArticles()
            }
        )
    }
}