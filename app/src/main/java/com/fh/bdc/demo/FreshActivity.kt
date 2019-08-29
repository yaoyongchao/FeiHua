package com.fh.bdc.demo

import com.fh.baselib.base.BaseActivity
import com.fh.bdc.R

class FreshActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_fresh
    }

    override fun initView() {
        addFragment(R.id.llyt_content,FreshFragment())
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
