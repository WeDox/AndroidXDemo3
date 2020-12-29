package com.onedream.androidxdemo3.framework.http.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedream.androidxdemo3.framework.http.custome.BodyOut

/**
 * @author jdallen
 * @since 2020/12/24
 */
open class BaseViewModel : ViewModel() {

    protected suspend fun <T> startApiRequest(block: suspend () -> T, onError: (String) -> Unit): Any {
        try {
            return block() as Any
        } catch (e: Exception) {
            handleException(e, onError)
            return Unit
        }
    }

    protected fun convertAndUpdateUI(liveData: MutableLiveData<BodyOut>, apiData: Any, onError: (String) -> Unit) {
        when (apiData) {
            is BodyOut -> {
                liveData.value = apiData
            }
            is Unit -> {  //上面代码出现异常时，data为kotlin.Unit，协程销毁或者解析错误，会为Unit

            }
            else -> {
                onError("数据强转失败$apiData")
            }
        }
    }

    private fun handleException(e: Exception, onError: (String) -> Unit) {
        when (e) {
            is com.google.gson.JsonSyntaxException -> {
                onError("JSON解析出错")
            }
            is java.net.ConnectException -> {
                onError("网络链接失败")
            }
           /* is kotlinx.coroutines.JobCancellationException ->{//JobCancellationException异常 todo 后期完善过滤掉这个异常，不必处理

            }*/
            else -> {
                if(e.javaClass.simpleName.equals("JobCancellationException")){//JobCancellationException异常 不处理

                }else {
                    onError("未知异常${e.toString()}" + "那你哈==" + e.javaClass.simpleName)
                }
            }
        }
    }
}
