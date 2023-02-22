package com.github.yangkl.retrofit_common.test

data class ResponseBean(
    val login: String?,
    val avatar_url: String?,
    val url: String?,
    val html_url: String?
){
    override fun toString(): String {
        return "{login = $login, avatar_url = $avatar_url, url = $url, html_url = $html_url}"
    }
}
