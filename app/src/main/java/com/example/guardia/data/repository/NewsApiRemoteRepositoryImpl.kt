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

    override fun getDomesticViolenceStories(): Flow<List<DomesticViolenceArticleResponse>> =
        dataSource.getDomesticViolenceStories()

    override fun getDomesticPsychologicalAbuseArticles(): Flow<List<DomesticViolenceArticleResponse>> =
        dataSource.getDomesticPsychologicalAbuseArticles()

    override fun getHarassmentAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>> =
        dataSource.getHarassmentAgainstWomenArticles()

    override fun getThreatAgainstWomenArticles(): Flow<List<DomesticViolenceArticleResponse>> =
        dataSource.getThreatAgainstWomenArticles()
}