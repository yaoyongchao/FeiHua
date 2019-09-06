package com.fh.bdc.ui.mine.setting

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.AppUtil
import com.fh.bdc.R
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_my_setting.*

@Route(path = RouteUrl.mysetting)
class MySettingActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_my_setting
    }

    override fun initView() {
        setToolbarTitle(R.string.setting)
        tv_code.text=(AppUtil.getVersionName(mContext))

    }

    override fun initData() {
    }

    override fun initListener() {
        fv_terms_of_service.setOnClickListener {
            JumpUtil.jumpActivityWithString(RouteUrl.webView,"https://www.baidu.com/")

        }
        fv_about_us.setOnClickListener {

        }

        btn_sign_out.setOnClickListener {

        }
    }

}
