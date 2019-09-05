package com.ygfh.doctor.ui.home

import com.fh.baselib.base.BaseFragment
import com.fh.bdc.R

/**
 * Author: YongChao
 * Date: 19-8-9 下午3:56
 * Description: 我的
 */
class MineFragment : BaseFragment() {
    companion object {
        // 单例模式： 双重校验锁式
        val instance: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MineFragment()
        }
    }
    override fun layoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
//        tv.text = "3"
//        rootView.tv1111.text = "3"
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}