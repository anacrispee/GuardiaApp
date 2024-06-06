package com.example.guardia.di.modules

import com.example.guardia.domain.core.ThreadContextProvider
import com.example.guardia.domain.use_case.GetDomesticViolenceArticlesUseCase
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
}