package com.onedream.androidxdemo3.framework.http.custome


import com.onedream.androidxdemo3.framework.utils.json_parse.OriginJsonParseUtils

import org.json.JSONException
import org.json.JSONObject

class BodyOut(jsonObject: JSONObject) {
    var code: Int = 0
    var data: String? = null
    var apiMsg: String? = null
        get() = if (field == null) "" else field

    init {
        Set(jsonObject)
    }

    fun Set(`object`: JSONObject) {
        try {
            code =if (OriginJsonParseUtils.getBoolean(`object`, "ret", false)) ApiCode.SUCCESS else -1
            data = OriginJsonParseUtils.getString(`object`, "info", "")
            apiMsg = OriginJsonParseUtils.getString(`object`, "errMsg", "")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    //返回码判断
    val isSuccess: Boolean
        get() = code == ApiCode.SUCCESS

    val isTokenError: Boolean
        get() = code == ApiCode.TOKEN_ERROR

    val isLuckDrawCoinNotEnough: Boolean
        get() = code == ApiCode.LUCK_DRAW_MOENY_NotEnough
}
