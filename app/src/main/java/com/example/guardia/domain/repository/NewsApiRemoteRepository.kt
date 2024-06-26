package com.example.guardia.domain.repository

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsApiRemoteRepository {
    fun getDomesticViolenceArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getDomesticViolenceStories(): Flow<List<DomesticViolenceArticleResponse>>
    fun getDomesticPsychologicalAbuseArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getHarassmentAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>>
    fun getThreatAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>>
}