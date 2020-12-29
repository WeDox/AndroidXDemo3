package com.onedream.androidxdemo3.framework.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context
    protected var mSavedInstanceState: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //
        mContext = this
        mSavedInstanceState = savedInstanceState
        initView()
        initData()
        initEvent()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {

        super.onDestroy()
    }
    //设置布局ID
    abstract  fun getLayoutId(): Int
    //初始化视图操作
    abstract fun initView()

    //初始化数据操作
    abstract fun initData()

    //初始化监听事件操作
    abstract fun initEvent()


    companion object {
        private val TAG = BaseActivity::class.java.name
    }
}
