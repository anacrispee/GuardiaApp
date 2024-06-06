package com.example.guardia.di.modules

import com.example.guardia.domain.core.ThreadContextProvider
import com.example.guardia.domain.use_case.GetDomesticPsychologicalAbuseArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceArticlesUseCase
import com.example.guardia.domain.use_case.GetDomesticViolenceStoriesUseCase
import com.example.guardia.domain.use_case.GetHarassmentAgainstWomenArticlesUseCase
import com.example.guardia.domain.use_case.GetThreatAgainstWomenArticlesUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetDomesticViolenceArticlesUseCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetDomesticViolenceStoriesUseCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetHarassmentAgainstWomenArticlesUseCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetThreatAgainstWomenArticlesUseCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetDomesticPsychologicalAbuseArticlesUseCase(
            scope = scope,
            repository = get()
        )
    }
}