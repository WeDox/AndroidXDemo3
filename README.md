# AndroidXDemo3
MVVM+Kotlin Coroutines+Retrofit2+OkHttp3+Jackson构建网络请求基础工程小例子

Kotlin 协程+ViewModel的实践小例子

#### 通过封装，使用更加简便（使用viewModelScope防止泄露问题）
~~~~~~~~~~~~
class RandomPoetryViewModel : BaseViewModel() {
    var resultListDataLiveData = MutableLiveData<BodyOut>()

    fun getListDataByPageNum(onError: (String) -> Unit) {
        //viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题
        viewModelScope.launch {
            //withContext表示挂起块
            val apiData = withContext(Dispatchers.IO) {
                 //发起网络请求
                startApiRequest({ MovieApi.getInstance()!!.sendRandomPoetry() }, onError)
            }
            //转换数据，通知UI更新
            convertAndUpdateUI(resultListDataLiveData, apiData, onError)
        }
    }


}
~~~~~~~~~~~~

