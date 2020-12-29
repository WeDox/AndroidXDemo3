package com.onedream.androidxdemo3.common.vm.random_poetry


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.onedream.androidxdemo3.framework.http.base.BaseViewModel
import com.onedream.androidxdemo3.framework.http.custome.BodyOut
import com.onedream.androidxdemo3.framework.http.custome.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author jdallen
 * @since 2020/12/24
 */
class RandomPoetryViewModel : BaseViewModel() {
    var resultListDataLiveData = MutableLiveData<BodyOut>()

    fun getListDataByPageNum(onError: (String) -> Unit) {
        //viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题
        viewModelScope.launch {
            //withContext表示挂起块
            val apiData = withContext(Dispatchers.IO) {
                startApiRequest({ MovieApi.getInstance()!!.sendRandomPoetry() }, onError)
            }
            //转换数据，通知UI更新
            convertAndUpdateUI(resultListDataLiveData, apiData, onError)
        }
    }


}


