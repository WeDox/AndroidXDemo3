package com.onedream.androidxdemo3.module.home.random_poetry.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.onedream.androidxdemo3.R
import com.onedream.androidxdemo3.common.bean.random_poetry.RandomPoetryBean
import com.onedream.androidxdemo3.databinding.ItemRandomPoetryBinding
import com.onedream.androidxdemo3.framework.base.BaseRecAdapter
import com.onedream.androidxdemo3.framework.base.BaseRecViewHolder

class RandomPoetryAdapter(
    private val dataBeans: List<RandomPoetryBean>,
    private val mContext: Context
) : BaseRecAdapter<RandomPoetryBean, RandomPoetryAdapter.ViewHolderItemNews>(dataBeans) {

    override fun onHolder(holder: ViewHolderItemNews, bean: RandomPoetryBean, position: Int) {
        val itemNewsBinding = holder.viewDataBinding
        itemNewsBinding.randomPoetry = dataBeans[position]
        itemNewsBinding.executePendingBindings()
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolderItemNews {
        val itemNewsBinding = DataBindingUtil.inflate<ItemRandomPoetryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_random_poetry, parent, false
        )
        return ViewHolderItemNews(itemNewsBinding)
    }


    override fun getItemCount(): Int {
        return dataBeans.size
    }


    class ViewHolderItemNews constructor(val viewDataBinding: ItemRandomPoetryBinding) :
        BaseRecViewHolder(viewDataBinding.root)
}