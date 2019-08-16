package com.ygfh.doctor.ui.demo

import com.fh.baselib.http.BaseObserver
import com.fh.baselib.utils.LogUtil
import com.fh.baselib.utils.rx.MyRxScheduler
import com.fh.bdc.bean.Login
import com.ygfh.doctor.net.DcServiceFactory
import java.util.concurrent.TimeUnit

/**
 * Author: YongChao
 * Date: 19-8-14 下午3:29
 * Description:
 */
class TwoPresenter: TwoContract.TwoPresenter() {
    override fun login() {
        DcServiceFactory.getService().login2()
                .delay(5, TimeUnit.SECONDS)
                .compose(getBindToLife())
                .compose(MyRxScheduler.ioMain(mContext))
                .subscribe(object : BaseObserver<Login>(){
                    override fun onSuccess(t: Login?) {
                        LogUtil.e("login:" + t!! + "-- " + getView())
                        getView()?.loginSuccess()
                    }
                })

    }

}