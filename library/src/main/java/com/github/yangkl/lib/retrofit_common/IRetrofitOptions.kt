package com.github.yangkl.lib.retrofit_common

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

interface IRetrofitOptions {

    /**
     * 得到HttpClient
     */
    fun getHttpClient(): OkHttpClient

    /**
     * 获得CallAdapter工厂类
     */
    fun getCallAdapterFactory(): CallAdapter.Factory?

    /**
     * 获得Converter工厂类
     */
    fun getConverterFactory(): Converter.Factory?
}