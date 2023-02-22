package com.github.yangkl.retrofit_common.test

import com.github.yangkl.lib.retrofit_common.BaseUrl
import retrofit2.http.GET
import retrofit2.http.Path

@BaseUrl("https://api.github.com/")
interface GithubService {


    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String): ResponseBean?
}