package com.ygfh.doctor.ui.home

import com.fh.baselib.base.BaseFragment
import com.fh.bdc.R
import kotlinx.android.synthetic.main.fragment_tab1.view.*

/**
 * Author: YongChao
 * Date: 19-8-9 下午3:56
 * Description:
 */
class Tab3Fragment : BaseFragment() {
    companion object {
        // 单例模式： 双重校验锁式
        val instance: Tab3Fragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Tab3Fragment()
        }
    }
    override fun layoutId(): Int {
        return R.layout.fragment_tab1
    }

    override fun initView() {
//        tv.text = "3"
        rootView.tv1111.text = "3"
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}