package com.example.guardia.data.remote.news_api

import com.example.guardia.data_remote.model.news_api.WomansViolenceArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsApiRemoteDataSource {
    fun getWomansViolenceArticles(): Flow<List<WomansViolenceArticleResponse>>
}