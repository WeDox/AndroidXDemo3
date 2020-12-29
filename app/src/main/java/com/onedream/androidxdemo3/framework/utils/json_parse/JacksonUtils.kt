package com.onedream.androidxdemo3.framework.utils.json_parse

import com.fasterxml.jackson.databind.type.TypeFactory
import com.onedream.androidxdemo3.framework.utils.system.LogHelper

object JacksonUtils {
    //解析对象
    fun <T> parseObject(response: String?, clazz: Class<T>): T? {
        try {
            return JacksonObjectMapper.getInstance().readValue(response, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            LogHelper.e("ATU", "解析出现异常$e")
            return null
        }

    }

    //解析数组
    fun <T> parseObjectList(response: String?, clazz: Class<T>): List<T>? {
        try {
            val typeFactory = JacksonObjectMapper.getInstance().typeFactory
            return JacksonObjectMapper.getInstance().readValue<List<T>>(
                response,
                typeFactory.constructCollectionType(List::class.java, clazz)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LogHelper.e("ATU", "解析出现异常$e")
            return null
        }

    }
}
