package com.voidx.data.network

import com.voidx.data.DataSettings
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor(private val settings: DataSettings): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("x-rapidapi-host", settings.serverHost)
            .addHeader("x-rapidapi-key", settings.apiKey)
            .build()

        return chain.proceed(request)
    }
}