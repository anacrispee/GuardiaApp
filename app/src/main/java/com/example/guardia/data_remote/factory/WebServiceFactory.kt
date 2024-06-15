package com.example.guardia.data_remote.factory

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object WebServiceFactory {
    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String = "",
    ): T {
        val gsonFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(gsonFactory)
            .build()

        return retrofit.create(T::class.java)
    }

    fun provideOkHttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .dispatcher(dispatcher())
            .addInterceptor(HttpLoggingInterceptor())
            .httpLoggingInterceptor(true)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()

    private fun dispatcher() = Dispatcher().apply {
        maxRequests = 1
        maxRequestsPerHost = 1
    }

    object UnitConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit,
        ): Converter<ResponseBody, *>? {
            return if (type == Unit::class.java) UnitConverter else null
        }

        private object UnitConverter : Converter<ResponseBody, Unit> {
            override fun convert(value: ResponseBody) {
                value.close()
            }
        }
    }

    private fun OkHttpClient.Builder.httpLoggingInterceptor(wasDebugVersion: Boolean) =
        when (wasDebugVersion) {
            true -> {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }

            else -> this
        }
}