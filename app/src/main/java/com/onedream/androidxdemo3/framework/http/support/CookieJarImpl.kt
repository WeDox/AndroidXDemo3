package com.onedream.androidxdemo3.framework.http.support

import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by mac on 2019/2/20.
 */

class CookieJarImpl : CookieJar {
    //cookie存储
    private val cookieStore = ConcurrentHashMap<String, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host()] = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieStore[url.host()]
        return cookies ?: ArrayList()
    }
}
