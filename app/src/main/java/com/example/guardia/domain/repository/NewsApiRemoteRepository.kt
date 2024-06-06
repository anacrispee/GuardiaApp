package com.example.guardia.domain.repository

import com.example.guardia.data_remote.model.news_api.WomansViolenceArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsApiRemoteRepository {
    fun getWomansViolenceArticles(): Flow<List<WomansViolenceArticleResponse>>
}