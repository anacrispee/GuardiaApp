package com.example.utils

import com.example.guardia.data_remote.model.news_api.generic_response.GenericResponseNewsApi
import com.example.guardia.data_remote.utils.RequestWrapper

class RequestWrapperTest : RequestWrapper {
    override suspend fun <T> wrapperGenericResponse(
        call: suspend () -> GenericResponseNewsApi<T>
    ): T {
        return  wrapper(call = call).articles ?: throw NullPointerException()
    }

    override suspend fun <D> wrapper(retryCount: Int, call: suspend () -> D): D {
        return  call()
    }
}