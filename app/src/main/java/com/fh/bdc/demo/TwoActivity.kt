package com.fh.bdc.demo

import com.fh.baselib.base.BaseActivity
import com.fh.baselib.http.BaseObserver
import com.fh.baselib.utils.LogUtil
import com.fh.baselib.utils.rx.MyRxScheduler
import com.fh.bdc.BuildConfig
import com.fh.bdc.R
import com.fh.bdc.bean.Login
import com.ygfh.doctor.net.DcServiceFactory
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_two
    }

    override fun initView() {
        tv.text = "状态--" + BuildConfig.DEBUG
    }

    override fun initData() {
        LogUtil.i("init1111")
    }

    override fun initListener() {
        LogUtil.i("init")
        btn1.setOnClickListener {
            DcServiceFactory.getService().login2()
//                .delay(5, TimeUnit.SECONDS)
                .compose(MyRxScheduler.ioMain())
                .subscribe(object : BaseObserver<Login>(){
                    override fun onSuccess(t: Login?) {
                        LogUtil.e("login:" + t!! + "-- " )
                    }

                })
        }
    }

}
