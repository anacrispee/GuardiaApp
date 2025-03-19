package com.example.guardia.features.feature_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardia.domain.use_case.GetDomesticPsychologicalAbuseArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceStoriesUseCase
import com.example.guardia.domain.use_case.GetHarassmentAgainstWomenArticlesUseCase
import com.example.guardia.domain.use_case.GetThreatAgainstWomenArticlesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class HomeViewModel : ViewModel(), KoinComponent {

    var viewState by mutableStateOf(HomeViewState())

    private val getDomesticViolenceArticlesUseCase: GetDomesticViolenceArticlesUseCase by inject { parametersOf(viewModelScope) }
    private val getDomesticViolenceStoriesUseCase: GetDomesticViolenceStoriesUseCase by inject { parametersOf(viewModelScope) }
    private val getDomesticPsychologicalAbuseArticlesUseCase: GetDomesticPsychologicalAbuseArticlesUseCase by inject { parametersOf(viewModelScope) }
    private val getHarassmentAgainstWomenArticlesUseCase: GetHarassmentAgainstWomenArticlesUseCase by inject { parametersOf(viewModelScope) }
    private val getThreatAgainstWomenArticlesUseCase: GetThreatAgainstWomenArticlesUseCase by inject { parametersOf(viewModelScope) }

    fun dispatcherViewAction(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.FetchDataByFilterOption -> fetchDataByFilterOption(
                id = action.id
            )
            is HomeViewAction.SearchNewsSubject -> searchNewsSubject(
                subject = action.subject
            )
            is HomeViewAction.UpdateIsEmpty -> viewState = viewState.copy(
                isEmptyState = action.value
            )

            HomeViewAction.GetDomesticViolenceArticles -> getDomesticViolenceArticles()
            HomeViewAction.GetDomesticViolenceStories -> getDomesticViolenceStories()
        }
    }

    private fun searchNewsSubject(subject: String) {
        if (subject.trim().isBlank() || subject.trim().length <= 3) {
            viewState = viewState.copy(
                isEmptyState = true
            )
        } else {
            validateIfIsOnThePredefinedList(subject)
        }
    }

    private fun validateIfIsOnThePredefinedList(subject: String) {
        val list = listVerticalFilters

        list.forEach { listItem ->
            if (listItem.searchableName.any { it == subject }) {
                viewState = viewState.copy(
                    searchId = listItem.id,
                    hasSearched = true,
                    isEmptyState = false,
                    wordMatched = true
                )
                getNews(id = listItem.id)
            } else if (viewState.wordMatched.not()) {
                viewState = viewState.copy(
                    isEmptyState = true
                )
            }
        }
    }

    private fun fetchDataByFilterOption(id: Int) {
        viewState = viewState.copy(
            isEmptyState = false,
            hasSearched = false
        )
        getNews(id)
    }

    private fun getNews(id: Int) {
        when (id) {
            FiltersEnum.PSYCHOLOGICAL_ABUSE.id -> getDomesticPsychologicalAbuseArticles()
            FiltersEnum.HARASSMENT.id -> getHarassmentAgainstWomenArticles()
            FiltersEnum.THREAT.id -> getThreatAgainstWomenArticles()
        }
    }

    private fun getDomesticViolenceArticles() {
        if (viewState.domesticViolencePopularArticles.isNullOrEmpty()) {
            viewState = viewState.copy(
                isLoading = true
            )
            getDomesticViolenceArticlesUseCase(
                onSuccess = {
                    viewState = viewState.copy(
                        isLoading = false,
                        domesticViolencePopularArticles = it,
                        isEmptyState = it.isEmpty()
                    )
                },
                onError = {
                    viewState = viewState.copy(
                        error = it,
                        isLoading = false,
                        isEmptyState = true
                    )
                }
            )
        }
    }

    private fun getDomesticViolenceStories() {
        if (viewState.domesticViolenceStories.isNullOrEmpty()) {
            viewState = viewState.copy(
                isLoading = true
            )
            getDomesticViolenceStoriesUseCase(
                onSuccess = {
                    viewState = viewState.copy(
                        isLoading = false,
                        domesticViolenceStories = it,
                        isEmptyState = it.isEmpty()

                    )
                },
                onError = {
                    viewState = viewState.copy(
                        error = it,
                        isLoading = false,
                        isEmptyState = true
                    )
                }
            )
        }
    }

    private fun getDomesticPsychologicalAbuseArticles() {
        if (viewState.domesticPsychologicalAbuseArticles.isNullOrEmpty()) {
            getDomesticPsychologicalAbuseArticlesUseCase(
                onSuccess = {
                    viewState = viewState.copy(
                        domesticPsychologicalAbuseArticles = it,
                        isEmptyState = it.isEmpty()
                    )
                },
                onError = {
                    viewState = viewState.copy(
                        error = it,
                        isEmptyState = true
                    )
                }
            )
        }
    }

    private fun getHarassmentAgainstWomenArticles() {
        if (viewState.harassmentAgainstWomenArticles.isNullOrEmpty()) {
            getHarassmentAgainstWomenArticlesUseCase(
                onSuccess = {
                    viewState = viewState.copy(
                        harassmentAgainstWomenArticles = it,
                        isEmptyState = it.isEmpty()
                    )
                },
                onError = {
                    viewState = viewState.copy(
                        error = it,
                        isEmptyState = true
                    )
                }
            )
        }
    }

    private fun getThreatAgainstWomenArticles() {
        if (viewState.threatAgainstWomenArticles.isNullOrEmpty()) {
            getThreatAgainstWomenArticlesUseCase(
                onSuccess = {
                    viewState = viewState.copy(
                        threatAgainstWomenArticles = it,
                        isEmptyState = it.isEmpty()
                    )
                },
                onError = {
                    viewState = viewState.copy(
                        error = it,
                        isEmptyState = true
                    )
                }
            )
        }
    }
}