package com.onedream.androidxdemo3.framework.base


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecAdapter<T, K : BaseRecViewHolder>(private val dataList: List<T>?) :
    RecyclerView.Adapter<K>() {
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
        context = parent.context
        return onCreateHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: K, position: Int) {
        onHolder(holder, dataList!![position], position)
    }


    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }


    abstract fun onCreateHolder(parent: ViewGroup, viewType: Int): K

    abstract fun onHolder(holder: K, bean: T, position: Int)

    /**
     * 通过资源res获得view
     */
    protected fun getViewByRes(res: Int): View {
        return LayoutInflater.from(context).inflate(res, null)
    }
}
