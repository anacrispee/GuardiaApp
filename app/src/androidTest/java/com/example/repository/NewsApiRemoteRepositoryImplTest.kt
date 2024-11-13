package com.example.repository

import com.example.guardia.data.remote.news_api.NewsApiRemoteDataSource
import com.example.guardia.data.repository.NewsApiRemoteRepositoryImpl
import com.example.utils.DummyFactory
import com.example.utils.testFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

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
        repository.getDomesticViolenceArticles().testFlow {
            assertEquals(
                expected = listOf(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE),
                actual = this
            )
        }
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

    @Test
    fun getDomesticViolenceStoriesMustReturnDomesticViolenceArticleResponse() {
        stubGetDomesticViolenceStories()
        repository.getDomesticViolenceStories().testFlow {
            assertEquals(
                expected = listOf(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE),
                actual = this
            )
        }
    }

    private fun stubGetDomesticViolenceStories() {
        whenever(dataSource.getDomesticViolenceStories()).thenReturn(
            flowOf(
                listOf(
                    DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE
                )
            )
        )
    }

    @Test
    fun getDomesticPsychologicalAbuseArticlesMustReturnDomesticViolenceArticleResponse() {
        stubGetDomesticPsychologicalAbuseArticles()
        repository.getDomesticPsychologicalAbuseArticles().testFlow {
            assertEquals(
                expected = listOf(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE),
                actual = this
            )
        }
    }

    private fun stubGetDomesticPsychologicalAbuseArticles() {
        whenever(dataSource.getDomesticPsychologicalAbuseArticles()).thenReturn(
            flowOf(
                listOf(
                    DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE
                )
            )
        )
    }

    @Test
    fun getHarassmentAgainstWomenArticlesMustReturnDomesticViolenceArticleResponse() {
        stubGetHarassmentAgainstWomenArticles()
        repository.getHarassmentAgainstWomenArticles().testFlow {
            assertEquals(
                expected = listOf(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE),
                actual = this
            )
        }
    }

    private fun stubGetHarassmentAgainstWomenArticles() {
        whenever(dataSource.getHarassmentAgainstWomenArticles()).thenReturn(
            flowOf(
                listOf(
                    DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE
                )
            )
        )
    }

    @Test
    fun getThreatAgainstWomenArticlesMustReturnDomesticViolenceArticleResponse() {
        stubGetThreatAgainstWomenArticles()
        repository.getThreatAgainstWomenArticles().testFlow {
            assertEquals(
                expected = listOf(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE),
                actual = this
            )
        }
    }

    private fun stubGetThreatAgainstWomenArticles() {
        whenever(dataSource.getThreatAgainstWomenArticles()).thenReturn(
            flowOf(
                listOf(
                    DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE
                )
            )
        )
    }
}