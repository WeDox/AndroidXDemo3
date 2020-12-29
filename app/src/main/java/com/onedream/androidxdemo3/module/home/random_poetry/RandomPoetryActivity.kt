package com.onedream.androidxdemo3.module.home.random_poetry

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onedream.androidxdemo3.R
import com.onedream.androidxdemo3.common.bean.random_poetry.RandomPoetryBean
import com.onedream.androidxdemo3.common.vm.random_poetry.RandomPoetryViewModel
import com.onedream.androidxdemo3.common.vm.random_poetry.RandomPoetryViewModelFactory
import com.onedream.androidxdemo3.databinding.ActivityRandomPoetryBinding
import com.onedream.androidxdemo3.framework.base.BaseActivity
import com.onedream.androidxdemo3.framework.http.custome.BodyOut
import com.onedream.androidxdemo3.framework.utils.json_parse.JacksonUtils
import com.onedream.androidxdemo3.framework.utils.system.LogHelper
import com.onedream.androidxdemo3.module.home.random_poetry.adapter.RandomPoetryAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RandomPoetryActivity : BaseActivity() {
    private lateinit var binding: ActivityRandomPoetryBinding
    //
    private var randomPoetryAdapter: RandomPoetryAdapter? = null
    private val randomPoetryList = ArrayList<RandomPoetryBean>()

    override fun getLayoutId(): Int {
        return R.layout.activity_random_poetry
    }

    override fun initView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun initData() {
        randomPoetryAdapter = RandomPoetryAdapter(randomPoetryList, mContext)
        binding.recycleView.layoutManager = LinearLayoutManager(mContext)
        binding.recycleView.adapter = randomPoetryAdapter
        initMyViewModel()
    }

    override fun initEvent() {

    }

    private fun initMyViewModel() {
        val randomPoetryViewModel = ViewModelProvider(
            this,
            RandomPoetryViewModelFactory()
        ).get(RandomPoetryViewModel::class.java)
        randomPoetryViewModel.resultListDataLiveData.observe(this, Observer<BodyOut> { bodyOut ->
            showUI(bodyOut)
        })

        randomPoetryViewModel.getListDataByPageNum {
            printLog("回调$it")
        }
    }

    private fun showUI(bodyOut: BodyOut) {
        binding.tvHint.visibility = View.GONE
        if (bodyOut.isSuccess) {
            val dataList = ArrayList<RandomPoetryBean>()
            val apiDataList =
                JacksonUtils.parseObjectList(bodyOut.data, RandomPoetryBean::class.java)
            if (null != apiDataList && apiDataList.isNotEmpty()) {
                dataList.addAll(apiDataList)
                randomPoetryList.clear()
                randomPoetryList.addAll(dataList)
                randomPoetryAdapter!!.notifyDataSetChanged()
            } else {
                printLog("返回数据为空")
            }
        } else {
            printLog("请求网络成功,但接口返回错误信息：" + bodyOut.apiMsg)
        }
    }

    private fun printLog(content: String) {
        LogHelper.e("ATU", content)
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(mContext, content, Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context, RandomPoetryActivity::class.java)
            context.startActivity(intent)
        }
    }
}
