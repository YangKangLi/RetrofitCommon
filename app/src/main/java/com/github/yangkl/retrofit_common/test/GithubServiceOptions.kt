package com.github.yangkl.retrofit_common.test

import android.util.Log
import com.github.yangkl.lib.retrofit_common.IRetrofitOptions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubServiceOptions : IRetrofitOptions {


    override fun getHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(
                "YangKL",
                "响应：$message"
            )
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()
    }

    override fun getCallAdapterFactory(): CallAdapter.Factory? {
        return RxJava2CallAdapterFactory.create()
    }

    override fun getConverterFactory(): Converter.Factory? {
        return GsonConverterFactory.create()
    }
}