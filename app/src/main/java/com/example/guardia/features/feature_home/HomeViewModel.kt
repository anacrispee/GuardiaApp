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

            HomeViewAction.GetDomesticViolenceArticles -> getDomesticViolenceArticles()
            HomeViewAction.GetDomesticViolenceStories -> getDomesticViolenceStories()
        }
    }

    private fun searchNewsSubject(subject: String) {
        if (subject.isBlank()) {
            viewState = viewState.copy(
                isEmptyState = false
            )
        } else if (subject.isNotBlank() && (subject.length >= 3)) {
            validateIfIsOnThePredefinedList(subject)
        }
    }

    private fun validateIfIsOnThePredefinedList(subject: String) {
        val list = listVerticalFilters

        list.forEach { filter ->
            if (filter.searchableName.contains(subject)) {
                fetchDataByFilterOption(id = filter.id)
                return
            } else {
                viewState = viewState.copy(
                    isEmptyState = true
                )
            }
        }
    }

    private fun fetchDataByFilterOption(id: Int) {
        viewState = viewState.copy(
            isEmptyState = false
        )
        when (id) {
            FiltersEnum.PSYCHOLOGICAL_ABUSE.id -> getDomesticPsychologicalAbuseArticles()
            FiltersEnum.HARASSMENT.id -> getHarassmentAgainstWomenArticles()
            FiltersEnum.THREAT.id -> getThreatAgainstWomenArticles()
        }
    }

    private fun getDomesticViolenceArticles() {
        viewState = viewState.copy(
            isLoading = true
        )
        getDomesticViolenceArticlesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    isLoading = false,
                    domesticViolencePopularArticles = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it,
                    isLoading = false
                )
            }
        )
    }

    private fun getDomesticViolenceStories() {
        viewState = viewState.copy(
            isLoading = true
        )
        getDomesticViolenceStoriesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    isLoading = false,
                    domesticViolenceStories = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it,
                    isLoading = false
                )
            }
        )
    }

    private fun getDomesticPsychologicalAbuseArticles() {
        getDomesticPsychologicalAbuseArticlesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    domesticPsychologicalAbuseArticles = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it
                )
            }
        )
    }

    private fun getHarassmentAgainstWomenArticles() {
        getHarassmentAgainstWomenArticlesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    harassmentAgainstWomenArticles = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it
                )
            }
        )
    }

    private fun getThreatAgainstWomenArticles() {
        getThreatAgainstWomenArticlesUseCase(
            onSuccess = {
                viewState = viewState.copy(
                    threatAgainstWomenArticles = it
                )
            },
            onError = {
                viewState = viewState.copy(
                    error = it
                )
            }
        )
    }
}