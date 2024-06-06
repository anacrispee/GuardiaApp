package com.example.guardia.data_remote.services

import com.example.guardia.AppConstants.NEWS_API_KEY
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.API_KEY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.BASE_PARAM
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM_DOMESTIC_PSYCHOLOGICAL_ABUSE
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM_DOMESTIC_VIOLENCE
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM_DOMESTIC_VIOLENCE_STORIES
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_PARAM_HARASSMENT_AGAINST_WOMEN
import com.example.guardia.data_remote.services.NewsApiService.NewsApiServiceConstants.QUERY_THREAT_AGAINST_WOMEN
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(BASE_PARAM)
    suspend fun getDomesticViolenceArticles(
        @Query(QUERY) query: String = QUERY_PARAM_DOMESTIC_VIOLENCE,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>

    @GET(BASE_PARAM)
    suspend fun getDomesticViolenceStories(
        @Query(QUERY) query: String = QUERY_PARAM_DOMESTIC_VIOLENCE_STORIES,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>

    @GET(BASE_PARAM)
    suspend fun getDomesticPsychologicalAbuseArticles(
        @Query(QUERY) query: String = QUERY_PARAM_DOMESTIC_PSYCHOLOGICAL_ABUSE,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>

    @GET(BASE_PARAM)
    suspend fun getHarassmentAgainstWomenArticles(
        @Query(QUERY) query: String = QUERY_PARAM_HARASSMENT_AGAINST_WOMEN,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>

    @GET(BASE_PARAM)
    suspend fun getThreatAgainstWomenArticles(
        @Query(QUERY) query: String = QUERY_THREAT_AGAINST_WOMEN,
        @Query(API_KEY) apiKey: String = NEWS_API_KEY
    ): GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>
    
    object NewsApiServiceConstants {
        const val BASE_PARAM = "everything"
        const val QUERY = "q"
        const val API_KEY = "apiKey"

        const val QUERY_PARAM_DOMESTIC_VIOLENCE = "domestic violence"
        const val QUERY_PARAM_DOMESTIC_VIOLENCE_STORIES = "domestic violence stories"
        const val QUERY_PARAM_DOMESTIC_PSYCHOLOGICAL_ABUSE = "domestic psychological abuse"
        const val QUERY_PARAM_HARASSMENT_AGAINST_WOMEN = "harassment against women"
        const val QUERY_THREAT_AGAINST_WOMEN = "threat against women"
    }
}