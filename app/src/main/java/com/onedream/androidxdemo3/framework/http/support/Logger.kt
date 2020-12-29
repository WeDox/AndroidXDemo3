package com.onedream.androidxdemo3.framework.http.support


import com.onedream.androidxdemo3.framework.utils.system.LogHelper

class Logger : LoggingInterceptor.Logger {

    override fun log(message: String) {
        LogHelper.e("ATU Logger", "http : $message")
    }
}
