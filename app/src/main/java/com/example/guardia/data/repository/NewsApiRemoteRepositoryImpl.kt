package com.example.guardia.data.repository

import com.example.guardia.data.remote.news_api.NewsApiRemoteDataSource
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.repository.NewsApiRemoteRepository
import kotlinx.coroutines.flow.Flow

class NewsApiRemoteRepositoryImpl(
    private val dataSource: NewsApiRemoteDataSource
) : NewsApiRemoteRepository {

    override fun getDomesticViolenceArticles(): Flow<List<DomesticViolenceArticleResponse>> =
        dataSource.getDomesticViolenceArticles()
}