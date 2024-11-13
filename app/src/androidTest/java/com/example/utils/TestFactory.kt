package com.example.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

fun <R> Flow<R>.testFlow(test: R.() -> Unit) {
    runBlocking {
        collect {
            it.test()
        }
    }
}