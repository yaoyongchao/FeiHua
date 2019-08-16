package com.fh.bdc.demo

import com.fh.baselib.mvp.MvpBaseActivity
import com.fh.baselib.utils.LogUtil
import com.fh.bdc.BuildConfig
import com.fh.bdc.R
import com.ygfh.doctor.ui.demo.TwoContract
import com.ygfh.doctor.ui.demo.TwoPresenter
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : MvpBaseActivity<TwoContract.TwoView,TwoPresenter>(),TwoContract.TwoView {


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

            mPresenter?.login()

//            showDialog()
//            loadingFragment.show(supportFragmentManager,"loading")
            /*DcServiceFactory.getService().login2()
                .delay(5, TimeUnit.SECONDS)
                .compose(MyRxScheduler.ioMain())
                .subscribe(object : BaseObserver<Login>(){
                    override fun onSuccess(t: Login?) {
                        LogUtil.e("login:" + t!! + "-- " )
                    }

                    override fun onFinish() {
                        super.onFinish()
                        dismissDialog()
                    }


                })*/
        }
    }

    override fun loginSuccess() {

    }
}
