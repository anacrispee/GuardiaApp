package com.example.guardia.data_remote.services

import com.example.guardia.AppConstants.NEWS_API_KEY
import com.example.guardia.data_remote.model.news_api.WomansViolenceArticleResponse
import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.API_KEY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getWomansViolenceArticles(
        @Query(QUERY) query: String = QUERY_PARAM,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<WomansViolenceArticleResponse>>

    object NewsApiServiceConstants {
        const val QUERY = "q"
        const val QUERY_PARAM = "violence against woman"
        const val API_KEY = "apiKey"
    }
}