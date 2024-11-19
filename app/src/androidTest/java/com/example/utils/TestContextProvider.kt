package com.example.utils

import com.example.guardia.domain.core.ThreadContextProvider
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ThreadContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}

val testModule = module {
    single { TestContextProvider() }
}