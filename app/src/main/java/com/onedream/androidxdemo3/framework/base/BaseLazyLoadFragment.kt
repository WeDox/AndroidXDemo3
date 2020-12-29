package com.onedream.androidxdemo3.framework.base

import androidx.fragment.app.Fragment

/**
 * @author Created by GammaQ.
 * @version V1.0
 * @FileName: com.cn100.client.base.BaseLazyLoadFragment.java
 * @date:2016-04-15 16:01
 * * 注1：
 * 如果是与viewPager一起使用，调用的是setUserVisibleHint
 * 注2：
 * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged
 * 针对初始就show的fragment 为了触发onHiddenChanged事件，达到lazy效果，需要先hide再show
 */
abstract class BaseLazyLoadFragment : Fragment() {

    protected var isFirstVisible = true//是否第一次可见
    protected var isFirstInVisible = true//是否第一次不可见
    protected var isLoaded = false//是否已加载数据
    protected var isViewPrepare = false//视图是否已加载

    /**
     * setUserVisibleHint会比onCreateView方法更早调用
     *
     * @param isVisibleToUser true可见false不可见
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false
                onFirstVisible()
            } else {
                onVisible()
            }
        } else {
            if (isFirstInVisible) {
                isFirstInVisible = false
                onFirstInVisible()
            } else {
                onInVisible()
            }
        }
    }


    /**
     * 如果通过FragmentTransaction的show和hide方法来控制显示，调用该方法
     *
     * @param hidden true:为show到hide  false:为hide到show
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            if (isFirstInVisible) {
                isFirstInVisible = false
                onFirstInVisible()
            } else {
                onInVisible()
            }
        } else {
            if (isFirstVisible) {
                isFirstVisible = false
                onFirstVisible()
            } else {
                onVisible()
            }
        }
    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    protected fun onInVisible() {}

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    protected fun onFirstInVisible() {}

    /**
     * fragment可见（切换回来或者onResume）
     */
    protected fun onVisible() {
        lazyLoad()
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    protected fun onFirstVisible() {
        lazyLoad()
    }

    /**
     * 判断是否第一次可见，数据是否已加载，视图是否已初始化，然后再进行懒加载
     */
    protected fun lazyLoad() {
        if (!isViewPrepare || isFirstVisible || isLoaded) return
        onLazyLoad()
        isLoaded = true
    }

    protected abstract fun onLazyLoad()


}

