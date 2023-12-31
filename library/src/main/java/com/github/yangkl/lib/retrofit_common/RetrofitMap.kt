package com.github.yangkl.lib.retrofit_common

import android.text.TextUtils
import android.util.Log
import retrofit2.Retrofit
import java.util.*

class RetrofitMap {

    private object SingletonHolder {
        val holder = RetrofitMap()
    }

    companion object {
        // 单例对象
        val instant = SingletonHolder.holder
    }

    /**
     * 创建或者获取Service
     */
    fun <T> createService(clazz: Class<T>, options: IRetrofitOptions): T? {
        val baseUrl = getBaseUrl(clazz, options)
        val retrofit = createRetrofit(baseUrl, options)
        Log.d("YangKL", "retrofit = ${retrofit.toString()}")
        return retrofit?.create(clazz)
    }

    /**
     * 获取BaseUrl
     */
    private fun <T> getBaseUrl(clazz: Class<T>, options: IRetrofitOptions): String {
        var baseUrl = options.getBaseUrl()
        if (!TextUtils.isEmpty(baseUrl)) {
            return baseUrl!!
        } else {
            Objects.requireNonNull(clazz, "RetrofitMap -> getBaseUrl: clazz is null")
            if (clazz.isAnnotationPresent(BaseUrl::class.java)) {
                baseUrl = clazz.getAnnotation(BaseUrl::class.java)?.base_url
                Objects.requireNonNull(baseUrl, "RetrofitMap -> getBaseUrl: base_url is null")
                return baseUrl!!
            } else {
                throw RuntimeException("RetrofitMap -> getBaseUrl: clazz should use BaseUrl Annotation")
            }
        }
    }

    /**
     *
     */
    private fun createRetrofit(baseUrl: String, options: IRetrofitOptions): Retrofit? {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(options.getHttpClient())
            // 添加CallAdapter工厂
            val callAdapter = options.getCallAdapterFactory()
            callAdapter?.let {
                addCallAdapterFactory(it)
            }
            // 添加ConverterAdapter工厂
            val converter = options.getConverterFactory()
            converter?.let {
                addConverterFactory(it)
            }
        }.build()
    }
}
