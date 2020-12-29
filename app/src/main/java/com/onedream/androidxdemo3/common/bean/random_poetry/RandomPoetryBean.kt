package com.onedream.androidxdemo3.common.bean.random_poetry

/**
 * @author jdallen
 * @since 2020/12/24
 */
class RandomPoetryBean {
    /**
     * id : 21822
     * title : 通州丁溪馆夜别李景信三首 一
     * author : 元稹
     * author_id : 117
     * paragraphList : [{"id":"119425","poetry_id":"21822","paragraph":"月蒙蒙兮山掩掩，束束别魂眉敛敛。","strain":"仄平平平平仄仄，仄仄仄平平仄仄。","p_desc":"","is_famous":"0"},{"id":"119426","poetry_id":"21822","paragraph":"蠡盏覆时天欲明，碧幌青灯风滟滟。","strain":"○仄仄平平仄平，仄仄平平平仄仄。","p_desc":"","is_famous":"0"},{"id":"119427","poetry_id":"21822","paragraph":"泪消语尽还暂眠，唯梦千山万山险。","strain":"仄平仄仄平仄平，仄仄平平仄平仄。","p_desc":"","is_famous":"0"}]
     */

    var id: String? = null
    var title: String? = null
    var author: String? = null
    var author_id: String? = null
    var paragraphList: List<ParagraphListBean>? = null

    class ParagraphListBean {
        /**
         * id : 119425
         * poetry_id : 21822
         * paragraph : 月蒙蒙兮山掩掩，束束别魂眉敛敛。
         * strain : 仄平平平平仄仄，仄仄仄平平仄仄。
         * p_desc :
         * is_famous : 0
         */

        var id: String? = null
        var poetry_id: String? = null
        var paragraph: String? = null
        var strain: String? = null
        var p_desc: String? = null
        var is_famous: String? = null
    }
}
