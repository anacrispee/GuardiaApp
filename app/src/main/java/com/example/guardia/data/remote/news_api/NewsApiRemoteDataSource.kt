package com.example.guardia.data.remote.news_api

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsApiRemoteDataSource {
    fun getDomesticViolenceArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getDomesticViolenceStories(): Flow<List<DomesticViolenceArticleResponse>>
    fun getDomesticPsychologicalAbuseArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getHarassmentAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getThreatAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>>
}