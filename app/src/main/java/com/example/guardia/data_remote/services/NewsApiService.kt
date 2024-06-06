package com.example.guardia.data_remote.services

import com.example.guardia.AppConstants.NEWS_API_KEY
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.API_KEY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.BASE_PARAM
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(BASE_PARAM)
    suspend fun getDomesticViolenceArticles(
        @Query(QUERY) query: String = QUERY_PARAM,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>
    
    object NewsApiServiceConstants {
        const val BASE_PARAM = "everything"
        const val QUERY = "q"
        const val API_KEY = "apiKey"
        const val QUERY_PARAM = "domestic violence"
    }
}