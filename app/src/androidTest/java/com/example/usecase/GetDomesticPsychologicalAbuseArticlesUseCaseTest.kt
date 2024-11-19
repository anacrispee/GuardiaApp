package com.example.usecase

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.repository.NewsApiRemoteRepository
import com.example.guardia.domain.use_case.GetDomesticPsychologicalAbuseArticlesUseCase
import com.example.utils.testFlow
import com.example.utils.testModule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetDomesticPsychologicalAbuseArticlesUseCaseTest {
    @Mock
    private lateinit var response: DomesticViolenceArticleResponse

    @Mock
    private lateinit var repository: NewsApiRemoteRepository
    private lateinit var subject: GetDomesticPsychologicalAbuseArticlesUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetDomesticPsychologicalAbuseArticlesUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun whenSucceedMustReturnListOfDomesticPsychologicalAbuseArticles() {
        stubOnSuccess()
        subject.run(
            params = null
        ).testFlow {
            assertEquals(response, this)
        }
    }

    private fun stubOnSuccess() {
        whenever(repository.getDomesticPsychologicalAbuseArticles()).thenReturn(
            flowOf(listOf(response))
        )
    }
}