package com.example.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.guardia.data_remote.datasource.NewsApiRemoteDataSourceImpl
import com.example.guardia.data_remote.model.news_api.ArticleSource
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import com.example.guardia.data_remote.services.NewsApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class NewsApiRemoteDataSourceImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //    private val requestWrapper by lazy { requestWrapperTest() }
    private lateinit var datasource: NewsApiRemoteDataSourceImpl
    private var webService: NewsApiService = mockk(relaxed = true)
    private val testModule = module {
//        single { requestWrapper }
        single { webService }
    }

    @Before
    fun setup() {
        stopKoin()
        startKoin { modules(testModule) }
        datasource = NewsApiRemoteDataSourceImpl(webService)
    }

    @After
    fun afterTest() {
         stopKoin()
    }

    @Test
    fun getDomesticViolenceArticlesMustReturnListOfDomesticViolenceArticleResponse() = runBlocking {
        val dummyResponse = GenericResponseNewsApi(
            status = "ok",
            totalResults = 1,
            articles = listOf(
                DomesticViolenceArticleResponse(
                    source = ArticleSource(
                        sourceId = "id",
                        sourceName = "name"
                    ),
                    author = "author",
                    title = "title",
                    description = "description",
                    url = "url",
                    urlToImage = "urlToImage",
                    publishedAt = "publishedAt",
                    content = "content"
                )
            )
        )

        stubGetDomesticViolenceArticles(dummyResponse)
    }

    private fun stubGetDomesticViolenceArticles(dummyResponse: GenericResponseNewsApi<List<DomesticViolenceArticleResponse>>) {
        coEvery {
            (webService.getDomesticViolenceArticles())
        } returns dummyResponse
    }
}