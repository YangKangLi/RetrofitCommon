package com.github.yangkl.retrofit_common.test

import com.github.yangkl.lib.retrofit_common.RetrofitMap

class GithubModel {

    private val serviceOptions = GithubServiceOptions()

    private var service: GithubService? =
        RetrofitMap.instant.createService(GithubService::class.java, serviceOptions)

    private object SingletonHolder {
        val holder = GithubModel()
    }

    companion object {
        // 单例对象
        val instant = SingletonHolder.holder
    }

    suspend fun getUserInfo(user: String): ResponseBean? {
        initService()
        return service?.getUserInfo(user)
    }

    private fun initService() {
        if (service == null) {
            service = RetrofitMap.instant.createService(GithubService::class.java, serviceOptions)
        }
    }


}