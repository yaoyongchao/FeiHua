package com.fh.bdc.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.bdc.R
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author: YongChao
 * Date: 19-8-16 上午11:50
 * Description: 登录
 */
@Route(path = RouteUrl.login)
class LoginActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initListener() {
        btn_login.setOnClickListener {
//            startActivity(Intent(mContext,TwoActivity::class.java))
            JumpUtil.jumpActivity(RouteUrl.home)
        }
    }
}
