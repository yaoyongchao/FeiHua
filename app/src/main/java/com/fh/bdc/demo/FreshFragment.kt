package com.fh.bdc.demo

import com.fh.baselib.base.BaseFragment
import com.fh.bdc.R

/**
 * Author: YongChao
 * Date: 19-8-29 下午3:55
 * Description:
 */
class FreshFragment : BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.activity_fresh
    }

    override fun initView() {
        setRefreshView(true)
    }

    override fun initListener() {
    }

    override fun initData() {
    }



}