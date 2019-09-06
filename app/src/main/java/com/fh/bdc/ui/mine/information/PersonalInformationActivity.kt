package com.fh.bdc.ui.mine.information

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.bdc.R
import com.fh.bdc.utils.RouteUrl

@Route(path = RouteUrl.personalInformation)
class PersonalInformationActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_personal_information
    }

    override fun initView() {
        setToolbarTitle("个人信息")
        showToolbar(true)
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun isFullScreen(): Boolean {
        return false
    }
}
