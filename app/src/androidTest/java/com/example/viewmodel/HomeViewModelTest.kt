package com.example.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse
import com.example.guardia.domain.core.ThreadContextProvider
import com.example.guardia.domain.use_case.GetDomesticPsychologicalAbuseArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceStoriesUseCase
import com.example.guardia.domain.use_case.GetHarassmentAgainstWomenArticlesUseCase
import com.example.guardia.domain.use_case.GetThreatAgainstWomenArticlesUseCase
import com.example.guardia.features.feature_home.HomeViewAction
import com.example.guardia.features.feature_home.HomeViewModel
import com.example.utils.DummyFactory
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    private val getDomesticViolenceArticlesUseCase: GetDomesticViolenceArticlesUseCase = mockk()
    private val getDomesticViolenceStoriesUseCase: GetDomesticViolenceStoriesUseCase = mockk()
    private val getDomesticPsychologicalAbuseArticlesUseCase: GetDomesticPsychologicalAbuseArticlesUseCase = mockk()
    private val getHarassmentAgainstWomenArticlesUseCase: GetHarassmentAgainstWomenArticlesUseCase = mockk()
    private val getThreatAgainstWomenArticlesUseCase: GetThreatAgainstWomenArticlesUseCase = mockk()

    private val testModule = module {
        single {
            ThreadContextProvider()
        }
        single { getDomesticViolenceArticlesUseCase() }
        single { getDomesticViolenceStoriesUseCase }
        single { getDomesticPsychologicalAbuseArticlesUseCase }
        single { getHarassmentAgainstWomenArticlesUseCase }
        single { getThreatAgainstWomenArticlesUseCase }
    }

    @Before
    fun before() {
        stopKoin()
        startKoin { modules(testModule) }
        viewModel = HomeViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getDomesticViolenceArticlesWhenReturnSuccessOrError() {
        stubGetDomesticViolenceArticles()
        viewModel.dispatcherViewAction(HomeViewAction.GetDomesticViolenceArticles)
        Assert.assertTrue(viewModel.viewState.domesticViolencePopularArticles != null)
        Assert.assertEquals(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE, viewModel.viewState.domesticViolencePopularArticles)
    }

    private fun stubGetDomesticViolenceArticles() {
        every {
            getDomesticViolenceArticlesUseCase(
                onError = any(),
                onSuccess = captureLambda()
            )
        } answers {
            lambda<(DomesticViolenceArticleResponse) -> Unit>().invoke(DummyFactory.DUMMY_DOMESTIC_VIOLENCE_ARTICLE)
        }
    }
}