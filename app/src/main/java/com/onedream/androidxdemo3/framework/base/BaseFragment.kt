package com.onedream.androidxdemo3.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

/**
 * Fragment基类
 */
abstract class BaseFragment : BaseLazyLoadFragment() {
    private var rootView: View? = null
    protected var mContext: FragmentActivity? = null


    /**
     * 获取布局id抽象方法
     *
     * @return 布局文件资源id
     */
    protected abstract val contentViewId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(contentViewId, container, false)
            this.mContext = activity
            bind(rootView)
            initData()
            initEvent()
            isViewPrepare = true//懒加载的标记，视图是否已加载
            lazyLoad()
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        val parent = rootView!!.parent as ViewGroup
        parent.removeView(rootView)
        return rootView
    }

    /**
     * 为dataBinding服务
     *
     * @param view 布局文件对象
     */
    protected abstract fun bind(view: View?)

    //初始化数据操作
    abstract fun initData()

    //初始化监听事件操作
    abstract fun initEvent()

    //

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        private val TAG = BaseFragment::class.java.name
    }
}
