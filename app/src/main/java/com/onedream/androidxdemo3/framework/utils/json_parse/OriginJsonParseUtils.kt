package com.onedream.androidxdemo3.framework.utils.json_parse

import org.json.JSONException
import org.json.JSONObject

/**
 * 原生Json解析工具类
 *
 * @author jdallen
 * @since 2020/7/22
 */
object OriginJsonParseUtils {

    @Throws(JSONException::class)
    @JvmOverloads
    fun getInt(jsonObject: JSONObject, keyName: String, defaultValue: Int = 0): Int {
        return if (jsonObject.has(keyName)) jsonObject.getInt(keyName) else defaultValue
    }

    @Throws(JSONException::class)
    @JvmOverloads
    fun getLong(jsonObject: JSONObject, keyName: String, defaultValue: Long = 0): Long {
        return if (jsonObject.has(keyName)) jsonObject.getLong(keyName) else defaultValue
    }

    @Throws(JSONException::class)
    @JvmOverloads
    fun getDouble(jsonObject: JSONObject, keyName: String, defaultValue: Double = 0.0): Double {
        return if (jsonObject.has(keyName)) jsonObject.getDouble(keyName) else defaultValue
    }

    @Throws(JSONException::class)
    @JvmOverloads
    fun getString(jsonObject: JSONObject, keyName: String, defaultValue: String = ""): String {
        return if (jsonObject.has(keyName)) jsonObject.getString(keyName) else defaultValue
    }

    @Throws(JSONException::class)
    @JvmOverloads
    fun getBoolean(jsonObject: JSONObject, keyName: String, defaultValue: Boolean = false): Boolean {
        return if (jsonObject.has(keyName)) jsonObject.getBoolean(keyName) else defaultValue
    }

}//Int
//Long
//Double
//String
//Boolean
