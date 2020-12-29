package com.onedream.androidxdemo3.framework.http.custome

object Constant {
    var BASE_API_HOST = "http://120.78.120.117/poetry/"//api
    //
    val API_RANDOM_POETRY = "/poetry.php"


    fun getRealUrl(shortUrl: String): String {
        return BASE_API_HOST + shortUrl
    }
}
