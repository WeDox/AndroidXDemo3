package com.onedream.androidxdemo3

import androidx.databinding.DataBindingUtil
import com.onedream.androidxdemo3.databinding.ActivityMainBinding
import com.onedream.androidxdemo3.framework.base.BaseActivity
import com.onedream.androidxdemo3.module.home.random_poetry.RandomPoetryActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun initData() {

    }

    override fun initEvent() {
        binding.tv.setOnClickListener {
            RandomPoetryActivity.actionStart(mContext)
        }
    }
}
