package com.onedream.androidxdemo3.framework.utils.system

import android.util.Log

import com.onedream.androidxdemo3.BuildConfig

/**
 * @author jdallen
 * @since 2015/4/30
 */
object LogHelper {

    private val IS_DEBUG = BuildConfig.DEBUG

    fun i(tag: String, msg: String?) {
        if (IS_DEBUG) {
            Log.i(tag, msg ?: "")
        }
    }

    fun v(tag: String, msg: String?) {
        if (IS_DEBUG) {
            Log.v(tag, msg ?: "")
        }
    }

    fun d(tag: String, msg: String?) {
        if (IS_DEBUG) {
            Log.d(tag, msg ?: "")
        }
    }

    fun w(tag: String, msg: String?) {
        if (IS_DEBUG) {
            Log.w(tag, msg ?: "")
        }
    }

    fun e(tag: String, msg: String?) {
        if (IS_DEBUG) {
            Log.e(tag, msg ?: "")
        }
    }
}
