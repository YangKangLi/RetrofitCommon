package com.github.yangkl.retrofit_common.test

import okhttp3.Interceptor
import okhttp3.Response

class TestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originRequest = chain.request()
        return chain.proceed(originRequest)
    }
}