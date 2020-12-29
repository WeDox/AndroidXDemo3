package com.onedream.androidxdemo3.framework.utils.json_parse

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Jackson库的ObjectMapper对象单例化对象类
 *
 * @author
 */
class JacksonObjectMapper
/**
 * 单例对象的构造方法，设置相关参数
 */
private constructor() : ObjectMapper() {

    init {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 忽略未知参数
        configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false) // 忽略异常的分支
        configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
        configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false) // 忽略未知参数解析失败
    }

    companion object {

        /**
         * 默认序列化UID
         */
        private val serialVersionUID = 1L

        /**
         * 单例异步锁，用于维持线程安全
         */
        private val syncLock = Any()

        /**
         * 单例ObjectMapper对象
         */
        private var instance: JacksonObjectMapper? = null

        /**
         * 获取�?��设置好的单例ObjectMapper对象
         */
        fun getInstance(): ObjectMapper {
            if (instance == null) {
                synchronized(syncLock) {
                    if (instance == null) {
                        instance = JacksonObjectMapper()
                    }
                }
            }
            return instance!!
        }
    }

}
