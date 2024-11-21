package com.example.usecase

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.repository.NewsApiRemoteRepository
import com.example.guardia.domain.use_case.GetThreatAgainstWomenArticlesUseCase
import com.example.utils.testFlow
import com.example.utils.testModule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.Test

class GetThreatAgainstWomenArticlesUseCaseTest : KoinTest {
    @MockK
    private lateinit var response: DomesticViolenceArticleResponse

    @MockK
    private lateinit var repository: NewsApiRemoteRepository
    private lateinit var subject: GetThreatAgainstWomenArticlesUseCase

    @Before
    fun before() {
        stopKoin()
        MockKAnnotations.init(this)
        startKoin { modules(testModule) }
        subject = GetThreatAgainstWomenArticlesUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
        unmockkAll()
    }

    @Test
    fun whenSucceedMustReturnListOfThreatAgainstWomenArticles() {
        stubOnSuccess()
        subject.run(
            params = null
        ).testFlow {
            assertEquals(listOf(response), this)
        }
    }

    private fun stubOnSuccess() {
        every { repository.getThreatAgainstWomenArticles() } returns flowOf(
            listOf(response)
        )
    }
}