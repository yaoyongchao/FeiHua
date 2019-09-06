package com.ygfh.doctor.ui.home

import com.fh.baselib.base.BaseFragment
import com.fh.bdc.R
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import kotlinx.android.synthetic.main.fragment_mine.view.*

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
        rootView.llyt_information.setOnClickListener {
            JumpUtil.jumpActivity(RouteUrl.personalInformation)
        }

        rootView.fv_voice_answer.setOnClickListener {

        }

        rootView.fv_my_video.setOnClickListener {

        }

        rootView.fv_setting.setOnClickListener {
            JumpUtil.jumpActivity(RouteUrl.mysetting)
        }
    }

    override fun initData() {
    }

}