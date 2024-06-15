package com.example.guardia.data_remote.utils

import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import org.koin.core.component.KoinComponent

class RequestWrapperImpl : RequestWrapper, KoinComponent {

    override suspend fun <T> wrapperGenericResponse(
        call: suspend () -> GenericResponseNewsApi<T>
    ): T {
        val result = wrapper(call = call)
        return getSuccessResult(result)
    }

    override suspend fun <D> wrapper(retryCount: Int, call: suspend () -> D): D {
        return try {
            call().also { result ->
                if (result is GenericResponseNewsApi<*>) {
                    getSuccessResult(result)
                }
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun <T> getSuccessResult(result: GenericResponseNewsApi<T>): T {
        return when (result.articles) {
            null -> throw NullPointerException()
            else -> result.articles
        }
    }
}