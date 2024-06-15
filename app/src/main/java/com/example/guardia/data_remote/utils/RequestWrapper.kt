package com.example.guardia.data_remote.utils

import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi

interface RequestWrapper {
    suspend fun <T> wrapperGenericResponse(
        call: suspend () -> GenericResponseNewsApi<T>
    ): T

    suspend fun <D> wrapper(
        retryCount: Int = 0,
        call: suspend () -> D
    ): D
}