package com.example.repository

import com.example.guardia.data.remote.news_api.NewsApiRemoteDataSource
import com.example.guardia.data.repository.NewsApiRemoteRepositoryImpl
import com.example.utils.DummyFactory
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class NewsApiRemoteRepositoryImplTest {
    @Mock
    private lateinit var dataSource: NewsApiRemoteDataSource
    private lateinit var repository: NewsApiRemoteRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = NewsApiRemoteRepositoryImpl(dataSource)
    }

    @Test
    fun getDomesticViolenceArticlesMustReturnDomesticViolenceArticleResponse() {
        stubGetDomesticViolenceArticles()

    }

    private fun stubGetDomesticViolenceArticles() {
        whenever(dataSource.getDomesticViolenceArticles()).thenReturn(
            flowOf(
                listOf(
                    DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE
                )
            )
        )
    }
}